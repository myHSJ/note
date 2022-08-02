package pattern.factory;

import pattern.processor.EnhanceExProcessor;
import pattern.processor.FrProcessor;
import pattern.processor.Processor;
import pattern.processor.ScoreProcessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//工厂模式
public class ProcessorFactory {

    static Map<String, List<Processor>> map = new HashMap<>();


    static {

        FrProcessor frProcessor = new FrProcessor();
        EnhanceExProcessor enhanceExProcessor = new EnhanceExProcessor();
        ScoreProcessor scoreProcessor = new ScoreProcessor();

        //责任链
        List<Processor> newUserProcessors = Arrays.asList(frProcessor, enhanceExProcessor, scoreProcessor);
        List<Processor> vipUserProcessors = Arrays.asList(frProcessor, scoreProcessor);
        List<Processor> normalUserProcessors = Arrays.asList(scoreProcessor);

        //使用MAP实现
        map.put("PU", normalUserProcessors);
        map.put("VU", vipUserProcessors);
        map.put("NU", newUserProcessors);
    }

    public static List<Processor> getProcessor(String userType) {
        return map.get(userType);
    }



}
