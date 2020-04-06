package com.donnatto.springbootbatch.step;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Writer implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> messages) throws Exception {
        for (String msg : messages) {
            System.out.println("Writing the data " + msg);
        }
    }
}