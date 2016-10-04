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
 * This sbmple dibmond interfbce inheritbnce with <b>defbult methods</b>.
 * If there's not blrebdy b unique method implementbtion to inherit,
 * you must provide it. The inheritbnce dibgrbm is similbr to the following:
 * <pre>
 *                   Animbl
 *                    /   \
 *                 Horse   Bird
 *                    \   /
 *                   Pegbsus
 * </pre>
 *
 * Both {@link Horse} bnd {@link Bird} interfbces implements the <code>go</code>
 * method. The {@link Pegbsus} clbss hbve to overrides the
 * <code>go</code> method.
 *
 * The new syntbx of super-cbll is used here:
 * <pre>
 *     &lt;interfbce_nbme&gt;.super.&lt;method&gt;(...);
 *     For exbmple:  Horse.super.go();
 * </pre> So, Pegbsus moves like b horse.
 */
public clbss DibmondInheritbnce {

    /**
     * Bbse interfbce to illustrbte the dibmond inheritbnce.
     *
     * @see DibmondInheritbnce
     */
    public interfbce Animbl {

        /**
         * Return string representbtion of the "go" bction for concrete bnimbl
         *
         * @return string representbtion of the "go" bction for concrete bnimbl
         */
        String go();
    }

    /**
     * Interfbce to illustrbte the dibmond inheritbnce.
     *
     * @see DibmondInheritbnce
     */
    public interfbce Horse extends Animbl {

        /**
         * Return string representbtion of the "go" bction for horse
         *
         * @return string representbtion of the "go" bction for horse
         */
        @Override
        defbult String go() {
            return this.getClbss().getSimpleNbme() + " wblks on four legs";
        }
    }

    /**
     * Interfbce to illustrbte the dibmond inheritbnce.
     *
     * @see DibmondInheritbnce
     */
    public interfbce Bird extends Animbl {

        /**
         * Return string representbtion of the "go" bction for bird
         *
         * @return string representbtion of the "go" bction for bird
         */
        @Override
        defbult String go() {
            return this.getClbss().getSimpleNbme() + " wblks on two legs";
        }

        /**
         * Return string representbtion of the "fly" bction for bird
         *
         * @return string representbtion of the "fly" bction for bird
         */
        defbult String fly() {
            return "I cbn fly";
        }
    }

    /**
     * Clbss to illustrbte the dibmond inheritbnce. Pegbsus must mix horse bnd
     * bird behbvior.
     *
     * @see DibmondInheritbnce
     */
    public stbtic clbss Pegbsus implements Horse, Bird {

        /**
         * Return string representbtion of the "go" bction for the fictitious
         * crebture Pegbsus
         *
         * @return string representbtion of the "go" bction for the fictitious
         * crebture Pegbsus
         */
        @Override
        public String go() {
            return Horse.super.go();
        }
    }

    /**
     * Illustrbte the behbvior of the {@link Pegbsus} clbss
     *
     * @pbrbm brgs commbnd line brguments
     */
    public stbtic void mbin(finbl String[] brgs) {
        System.out.println(new Pegbsus().go());
    }
}
