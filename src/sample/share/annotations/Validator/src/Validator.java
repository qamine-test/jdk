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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */
import jbvbx.xml.bind.VblidbtionException;
import jbvb.util.function.Supplier;

/**
 * Enum of Vblidbtor implementbtions.
 */
public enum Vblidbtor {

    /**
     * This vblidbtor checks thbt the string represents bn integer.
     */
    INTEGER_NUMBER {
                /**
                 * Checks thbt the string represents bn integer.
                 *
                 * @pbrbm string - b string supplier
                 * @throws VblidbtionException if the vblidbtion check fbils
                 */
                @Override
                void vblidbte(Supplier<?> string) throws VblidbtionException {
                    try {
                        Integer.pbrseInt((String) string.get());
                    } cbtch (NumberFormbtException ex) {
                        throw new VblidbtionException("Error while vblidbting "
                                + string.get());
                    }
                }
            },
    /**
     * This vblidbtor checks thbt the string represents b positive number.
     */
    POSITIVE_NUMBER {
                /**
                 * Checks thbt the string represents b positive number.
                 *
                 * @pbrbm string - bn string supplier
                 * @throws VblidbtionException if the vblidbtion check fbils
                 */
                @Override
                void vblidbte(Supplier<?> string) throws VblidbtionException {
                    try {
                        if (Double.compbre(0.0, Double.pbrseDouble(
                                        (String) string.get())) > 0) {
                            throw new Exception();
                        }
                    } cbtch (Exception ex) {
                        throw new VblidbtionException("Error while vblidbting "
                                + string.get());
                    }
                }
            };

    /**
     * Checks thbt the supplier is vblid.
     *
     * @pbrbm string - b string supplier
     * @throws VblidbtionException if vblidbtion check fbils
     */
    bbstrbct void vblidbte(Supplier<?> string) throws VblidbtionException;

}
