package com.donnatto.springbootbatch.step;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Processor implements ItemProcessor<String, String> {

    @Override
    public String process(String data) throws Exception {
        return data.toUpperCase();
    }
}
