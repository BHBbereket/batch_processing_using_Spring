package com.bereket.springbatchpre.configration;

import com.bereket.springbatchpre.entity.Student;
import com.bereket.springbatchpre.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfigration {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private StudentRepo studentRepo;
    @Bean
    public FlatFileItemReader<Student> reader(){
        FlatFileItemReader<Student> itemReader=new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/dummy_data.csv")); // read the date from the file in resource folder
        itemReader.setName("csvReader");//name the file reader
        itemReader.setLinesToSkip(1);// we skip the first line of the header
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Student> lineMapper() {
        DefaultLineMapper<Student> lineMapper=new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer= new DelimitedLineTokenizer();// this tokenizer is used to separate the data in the row of the csv file
        tokenizer.setDelimiter(",");// in csv file the columns inside a row separated by comma so we use comma as a delimiter
        tokenizer.setStrict(false);// this is use not to be strict
        tokenizer.setNames("id","firstname","lastname","dob","gpa");

        BeanWrapperFieldSetMapper<Student> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);// this help as to map the csv file columns to map with the fileds of the entity

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
    @Bean
    public StudentProcessor processor(){
        return new StudentProcessor();
    }
    @Bean
    public RepositoryItemWriter<Student> writer(){
        RepositoryItemWriter<Student> writer=new RepositoryItemWriter<>();
        writer.setRepository(studentRepo);
        writer.setMethodName("save");
        return writer;
    }
    @Bean
    public Step step(){
        return stepBuilderFactory.get("csv-step").<Student,Student>chunk(8)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    @Bean
    public Job job(){
        return jobBuilderFactory.get("importStudents")
                .flow(step())
                .end().build();
    }


}
