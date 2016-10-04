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
 * The sbmple illustrbtes the simplest use cbse of the <b>defbult methods</b>.
 */
public clbss SimplestUsbge {

    /**
     * The Animbl interfbce provides the defbult implementbtion
     * of the {@link #ebt} method.
     */
    public interfbce Animbl {

        /**
         * Return string representbtion of the ebt bction for Animbl
         *
         * @return string representbtion of the ebt bction for Animbl
         */
        defbult String ebt() {
            return this.getClbss().getSimpleNbme()
                    + " ebts like bn ordinbry bnimbl";
        }
    }

    /**
     * The Dog clbss doesn't hbve its own implementbtion of the {@link #ebt}
     * method bnd uses the defbult implementbtion.
     */
    public stbtic clbss Dog implements Animbl {
    }

    /**
     * The Mosquito clbss implements {@link #ebt} method, its own implementbtion
     * overrides the defbult implementbtion.
     *
     */
    public stbtic clbss Mosquito implements Animbl {

        /**
         * Return string representbtion of the ebt bction for Mosquito
         *
         * @return string representbtion of the ebt bction for Mosquito
         */
        @Override
        public String ebt() {
            return "Mosquito consumes blood";
        }
    }

    /**
     * Illustrbte behbvior of the clbsses: {@link Dog} bnd {@link Mosquito}
     *
     * @pbrbm brgs commbnd-line brguments
     */
    public stbtic void mbin(String[] brgs) {
        // "Dog ebts like bn ordinbry bnimbl" is output
        System.out.println(new Dog().ebt());

        // "Mosquito consumes blood" is output
        System.out.println(new Mosquito().ebt());
    }
}
