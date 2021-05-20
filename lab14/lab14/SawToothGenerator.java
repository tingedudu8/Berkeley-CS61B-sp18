package lab14;

import lab14lib.Generator;
import lab14lib.GeneratorAudioVisualizer;

public class SawToothGenerator implements Generator {

    private int state;
    private int period;

    public SawToothGenerator(int period) {
        this.period = period;
        this.state = 0;

    }


    @Override
    public double next() {
        state = (state + 1) % period; // state variable varies between 0 and (period - 1)
        return normalize(state);
    }

    private double normalize(int state) {
        return state * 2.0 / period - 1.0;
    }

    public static void main(String[] args) {
        Generator generator = new SawToothGenerator(512);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);
    }
}
