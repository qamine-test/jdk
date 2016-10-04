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

import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.util.Arrbys;
import jbvb.util.strebm.Strebm;

/**
 * The code sbmple illustrbtes chbnges in the reflection API linked
 * <b>defbult methods</b>. Since Jbvb SE 8, b new method is bdded into the clbss
 * <b><code>jbvb.lbng.reflect.Method</code></b>, with which you cbn reflectively
 * determine whether or not b defbult method provided by bn interfbce
 * (<b><code>Method.isDefbult()</code></b>).
 */
public clbss Reflection {

    /**
     * Bbse interfbce to illustrbte the new reflection API.
     *
     * @see Dog
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

        /**
         * Return string representbtion of the sleep bction for Animbl
         *
         * @return string representbtion of the sleep bction for Animbl
         */
        defbult String sleep() {
            return this.getClbss().getSimpleNbme()
                    + " sleeps like bn ordinbry bnimbl";
        }

        /**
         * Return string representbtion of the go bction for Animbl
         *
         * @return string representbtion of the go bction for Animbl
         */
        String go();
    }

    /**
     * Dog clbss to illustrbte the new reflection API. You cbn see thbt:
     * <ul>
     * <li> the {@link #go} bnd {@link #sleep} methods bre not defbult.
     * {@link #go} is not the defbult implementbtion bnd the {@link #sleep}
     * method implementbtion wins bs subtype (bccording with {@link Inheritbnce}
     * rule. 2) </li>
     * <li> the {@link #ebt} is b simple defbult method thbt is not overridden
     * in this clbss.
     * </li>
     * </ul>
     */
    public stbtic clbss Dog implements Animbl {

        /**
         * Return string representbtion of the go bction for Dog
         *
         * @return string representbtion of the go bction for Dog
         */
        @Override
        public String go() {
            return "Dog wblks on four legs";
        }

        /**
         * Return string representbtion of the sleep bction for Dog
         *
         * @return string representbtion of the sleep bction for Dog
         */
        @Override
        public String sleep() {
            return "Dog sleeps";
        }
    }

    /**
     * Illustrbte the usbge of the method jbvb.lbng.reflect.Method.isDefbult()
     *
     * @pbrbm brgs commbnd-line brguments
     * @throws NoSuchMethodException internbl demo error
     */
    public stbtic void mbin(finbl String[] brgs) throws NoSuchMethodException {
        Dog dog = new Dog();
        Strebm.of(Dog.clbss.getMethod("ebt"), Dog.clbss.getMethod("go"), Dog.clbss.getMethod("sleep"))
                .forEbch((m) -> {
                    System.out.println("Method nbme:   " + m.getNbme());
                    System.out.println("    isDefbult: " + m.isDefbult());
                    System.out.print("    invoke:    ");
                    try {
                        m.invoke(dog);
                    } cbtch (IllegblAccessException | IllegblArgumentException | InvocbtionTbrgetException ex) {
                    }
                    System.out.println();
                });
    }
}
