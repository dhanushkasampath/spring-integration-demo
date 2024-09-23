package com.learn.spring_integration_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;

import java.io.File;

@Configuration
@EnableIntegration
public class SpringIntegrationConfig {

    // We have given poller limit to 1s. So in each second, it goes and check the channel for new files.
    @Bean //annotate as bean and make as Input-adapter
    @InboundChannelAdapter(value = "myFileInputChannel", poller = @Poller(fixedDelay = "1000"))// we can give any name as the channel
    public FileReadingMessageSource fileReadingMessageSource(){

        // note-5: lets say we want only to copy .txt files only to destination directory.
        // for that we need to specify a filter as follows
        CompositeFileListFilter<File> filter = new CompositeFileListFilter<>();
        filter.addFilter(new SimplePatternFileListFilter("*.txt")); // note-6 after applying this change as in
        // img-4.png we can see only .txt files are copies to destination directory. If we update the file in source directory it will reflect in the destination directory


        FileReadingMessageSource reader = new FileReadingMessageSource();
        //note-1: Then specify the directory name. So that this adapter will read files in this directory.
        //This may contain any kind of files
        reader.setDirectory(new File("/home/dhanushka/Pictures/Wallpapers"));
        reader.setFilter(filter);
        return reader;
    }

    // note-2: we created a one bean for reader above. now we will create another bean for writer.
    @Bean
    @ServiceActivator(inputChannel = "myFileInputChannel") // note-4: This serviceActivate first go and read the input channel. If he found something, go and write in the destination directory
    public FileWritingMessageHandler fileWritingMessageHandler(){
        //note-3: lets assume we don't have the below directory and it should be auto created by spring integration framework.
        // for that we need to writer.setAutoCreateDirectory(true)
        FileWritingMessageHandler writer =
                new FileWritingMessageHandler(new File("/home/dhanushka/Pictures/wallpapers-copy"));
        writer.setAutoCreateDirectory(true);
        writer.setExpectReply(false);
        return writer;
    }
}
