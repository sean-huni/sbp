package io.vultr.cld.paycons.config.batch;

import io.vultr.cld.paycons.domain.TxDto;
import io.vultr.cld.paycons.mapper.TxMapper;
import io.vultr.cld.paycons.persistence.entity.Tx;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import javax.sql.DataSource;
import java.net.MalformedURLException;

@Configuration
@EnableBatchProcessing
@Log4j2
public class SpringBatchConfig {

    private final TxMapper txMapper;

    public SpringBatchConfig(TxMapper txMapper) {
        this.txMapper = txMapper;
    }

    @Bean
    @StepScope
    public Resource resource(@Value("#{jobParameters['fileName']}") String fileName) throws MalformedURLException {
        return new UrlResource(fileName);
    }

    @Bean
    public FlatFileItemReader<TxDto> itemReader(Resource resource) {
        return new FlatFileItemReaderBuilder<TxDto>()
                .name("txItemReader")
                .resource(resource)
                .delimited()
                //id, ref, date, time, descr, type, amount
                .names("id", "ref", "date", "time", "descr", "type", "amount")
//                .fieldSetMapper(fieldSet -> {
//                    log.info("field names: {}", fieldSet.getNames());
//                    return null;
//                })
                .targetType(TxDto.class)
                .linesToSkip(1)
                .build();
    }

    @Bean
    public ItemProcessor<TxDto, Tx> itemProcessor() {
        return txDto -> txMapper.convertToTx(txDto);
    }

    @Bean
    public JdbcBatchItemWriter<Tx> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Tx>()
                .dataSource(dataSource)
                .sql("INSERT INTO tx (date, time, descr, type, amount) VALUES (:date, :time, :descr, :type, :amount)")
                .beanMapped()
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobs, StepBuilderFactory steps,
                   DataSource dataSource, Resource resource) throws Exception {
        return jobs.get("job")
                .start(steps.get("step")
                        .<TxDto, Tx>chunk(3)
                        .reader(itemReader(resource))
                        .processor(itemProcessor())
                        .writer(itemWriter(dataSource))
                        .build())
                .build();
    }
}
