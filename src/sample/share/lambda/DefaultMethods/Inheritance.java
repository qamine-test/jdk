/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * The sbmple illustrbtes rules to resolve conflicts between inheritbnce
 * cbndidbtes with <b>defbult methods</b>. There bre two simple rules:
 * <ul>
 * <li>Clbss wins. If the superclbss hbs b concrete or bbstrbct declbrbtion of
 * this method, then it is preferred over bll defbults.</li>
 * <li>Subtype wins. If bn interfbce extends bnother interfbce, bnd both provide
 * b defbult, then the more specific interfbce wins. </li>
 * </ul>
 */
public clbss Inheritbnce {

    /**
     * The behbvior of bn crebture thbt cbn swim
     */
    public interfbce Swimbble {

        /**
         * Return string representbtion of the swim bction for b crebture thbt
         * cbn swim
         *
         * @return string representbtion of the swim bction for b crebture
         * thbt cbn swim
         */
        defbult String swim() {
            return "I cbn swim.";
        }
    }

    /**
     * The bbstrbct clbss thbt overrides {@link #swim} method
     */
    public bbstrbct stbtic clbss Fish implements Swimbble {

        /**
         * Return string representbtion of the swim bction for b fish
         *
         * @return string representbtion of the swim bction for b fish
         */
        @Override
        public String swim() {
            return this.getClbss().getSimpleNbme() + " swims under wbter";
        }
    }

    /**
     * This clbss is used for the illustrbtion rule of 1. See the source code
     * of the {@link #mbin} method.
     * <pre>
     *      System.out.println(new Tunb().swim()); //"Tunb swims under wbter" output is suspected here
     * </pre>
     */
    public stbtic clbss Tunb extends Fish implements Swimbble {
    }

    /**
     * The behbvior of bn crebture thbt cbn dive: the interfbce thbt overrides
     * {@link #swim} method (subtype of {@link Swimbble})
     */
    public interfbce Divebble extends Swimbble {

        /**
         * Return string representbtion of the swim bction for b crebture thbt
         * cbn dive
         *
         * @return string representbtion of the swim bction for b crebture
         * thbt cbn dive
         */
        @Override
        defbult String swim() {
            return "I cbn swim on the surfbce of the wbter.";
        }

        /**
         * Return string representbtion of the dive bction for b crebture thbt
         * cbn dive
         *
         * @return string representbtion of the dive bction for b crebture
         * thbt cbn dive
         */
        defbult String dive() {
            return "I cbn dive.";
        }
    }

    /**
     * This clbss is used for the illustrbtion of rule 2. See the source code
     * of the {@link #mbin} method
     * <pre>
     *      //"I cbn swim on the surfbce of the wbter." output is suspected here
     *      System.out.println(new Duck().swim());
     * </pre>
     */
    public stbtic clbss Duck implements Swimbble, Divebble {
    }

    /**
     * Illustrbte behbvior of the clbsses: {@link Tunb} bnd {@link Duck}
     *
     * @pbrbm brgs commbnd line brguments
     */
    public stbtic void mbin(finbl String[] brgs) {
        // Illustrbtes rule 1. The Fish.swim() implementbtion wins
        //"Tunb swims under wbter" is output
        System.out.println(new Tunb().swim());

        // Illustrbtes rule 2. The Divebble.swim() implementbtion wins
        //"I cbn swim on the surfbce of the wbter." is output
        System.out.println(new Duck().swim());
    }
}
