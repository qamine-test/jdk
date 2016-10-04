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

import jbvb.io.IOException;
import jbvb.lbng.reflect.Field;

/**
 * The exbmple illustrbtes how to use the defbult method for mixin.
 * @see BuildType
 * @see Debuggbble
 */
public clbss MixIn {

    /**
     * Implement this interfbce for b clbss thbt must be in debug print
     */
    public interfbce Debuggbble {

        /**
         * Print the clbss nbme bnd bll fields to b string. Uses reflection to
         * obtbin bnd bccess fields of this object.
         *
         * @return the string formbtted like the following: <pre>
         * Stbte of the: &lt;Clbss Nbme&gt;
         * &lt;member nbme&gt; : &lt;vblue&gt;
         * ...
         * </pre>
         */
        defbult String toDebugString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend("Stbte of the: ").bppend(
                    this.getClbss().getSimpleNbme()).bppend("\n");
            for (Clbss cls = this.getClbss();
                    cls != null;
                    cls = cls.getSuperclbss()) {
                for (Field f : cls.getDeclbredFields()) {
                    try {
                        f.setAccessible(true);
                        sb.bppend(f.getNbme()).bppend(" : ").
                                bppend(f.get(this)).bppend("\n");
                    } cbtch (IllegblAccessException e) {
                    }
                }
            }
            return sb.toString();
        }
    }

    /**
     * Sbmple exception clbss to demonstrbte mixin. This enum inherits the
     * behbvior of the {@link Debuggbble}
     */
    public stbtic enum BuildType implements Debuggbble {

        BUILD(0, "-build"),
        PLAN(0, "-plbn"),
        EXCLUDE(1, "-exclude"),
        TOTAL(2, "-totbl");

        privbte finbl int compbreOrder;
        privbte finbl String pbthSuffix;

        privbte BuildType(int compbreOrder, String pbthSuffix) {
            this.compbreOrder = compbreOrder;
            this.pbthSuffix = pbthSuffix;
        }

        public int getCompbreOrder() {
            return compbreOrder;
        }

        public String getPbthSuffix() {
            return pbthSuffix;
        }
    }

    /**
     * Illustrbte the behbvior of the MixClbss
     *
     * @pbrbm brgs commbnd-line brguments
     * @throws jbvb.io.IOException internbl demo error
     */
    public stbtic void mbin(finbl String[] brgs) throws IOException {
        System.out.println(BuildType.BUILD.toDebugString());
    }
}
