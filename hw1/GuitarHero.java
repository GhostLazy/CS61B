/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String keyboard1 = "z_x_cv_b_n_ma_s_df_g_h_jq_w_er_t_y_u";
    private static final String keyboard2 = ",_._/0_1_2_3k_l_;'_4_5_6i_o_p[_]_7_8";
    private static final String keyboard = "z_x_cv_b_n_ma_s_df_g_h_jq_w_er_t_y_u,_._/0_1_2_3k_l_;'_4_5_6i_o_p[_]_7_8";
    private static final int totalNotes1 = keyboard1.length();
    private static final int totalNotes2 = keyboard2.length();

    private static double ithConcert(int i) {
        return CONCERT_A * Math.pow(2, (i - 21) / 12.0);
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString[] gs = new synthesizer.GuitarString[totalNotes1 + totalNotes2];
        for (int i = 0; i < totalNotes1; i++) {
            gs[i] = new synthesizer.GuitarString(ithConcert(i));
        }
        for (int i = 0; i < totalNotes2; i++) {
            gs[totalNotes1 + i] = new synthesizer.GuitarString(ithConcert(i + 12));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0) {
                    continue;
                }
                gs[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (synthesizer.GuitarString string: gs) {
                sample += string.sample();
            }
            StdAudio.play(sample);
            for (synthesizer.GuitarString string: gs) {
                string.tic();
            }
        }
    }
}

