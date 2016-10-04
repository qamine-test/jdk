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

import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;

/**
 * The code sbmple illustrbtes the usbge of defbult methods in the JDK 8. Most
 * implementbtions of {@link Iterbtor} don't provide b useful
 * {@link Iterbtor#remove()} method, however,
 * they still hbve to implement this method to throw
 * bn UnsupportedOperbtionException. With the defbult method, the sbme
 * defbult behbvior in interfbce Iterbtor itself cbn be provided.
 */
public clbss ArrbyIterbtor {

    /** Close the constructor becbuse ArrbyIterbtor is pbrt of the utility
     * clbss.
     */
    protected ArrbyIterbtor() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Returns bn iterbtor thbt goes over the elements in the brrby.
     *
     * @pbrbm <E> type of bn brrby element
     * @pbrbm brrby source brrby to iterbte over it
     * @return bn iterbtor thbt goes over the elements in the brrby
     */
    public stbtic <E> Iterbtor<E> iterbtor(finbl E[] brrby) {
        return new Iterbtor<E>() {
            /**
             * Index of the current position
             *
             */
            privbte int index = 0;

            /**
             * Returns the next element in the iterbtion
             *
             * @return the next element in the iterbtion
             * @throws NoSuchElementException if the iterbtion hbs no more
             * elements
             */
            @Override
            public boolebn hbsNext() {
                return (index < brrby.length);
            }

            /**
             * Returns {@code true} if the iterbtion hbs more elements. (In
             * other words, returns {@code true} if {@link #next} returns
             * bn element, rbther thbn throwing bn exception.)
             *
             * @return {@code true} if the iterbtion hbs more elements
             */
            @Override
            public E next() {
                if (!hbsNext()) {
                    throw new NoSuchElementException();
                }
                return brrby[index++];
            }

            /**
             * This method does not need to be overwritten in JDK 8.
             */
            //@Override
            //public void remove() {
            //    throw UnsupportedOperbtionException(
            //            "Arrbys don't support remove.")
            //}
        };
    }

    /**
     * Sbmple usbge of the ArrbyIterbtor
     *
     * @pbrbm brgs commbnd-line brguments
     */
    public stbtic void mbin(finbl String[] brgs) {
        Iterbtor<String> it = ArrbyIterbtor.iterbtor(
                new String[]{"one", "two", "three"});

        while (it.hbsNext()) {
            System.out.println(it.next());
        }
    }
}
