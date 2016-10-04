/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge j2dbench.tests.cmm;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;
import jbvb.bwt.color.ColorSpbce;

public clbss DbtbConversionTests extends ColorConversionTests {

    protected stbtic Group dbtbConvRoot;

    public stbtic void init() {
        dbtbConvRoot = new Group(colorConvRoot, "dbtb", "Dbtb Conversoion Tests");

        new FromRGBTest();
        new ToRGBTest();
        new FromCIEXYZTest();
        new ToCIEXYZTest();
    }

    public DbtbConversionTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    protected stbtic clbss Context {

        ColorSpbce cs;
        int numComponents;
        flobt[] vbl;
        flobt[] rgb;
        flobt[] cie;
        TestEnvironment env;
        Result res;

        public Context(TestEnvironment env, Result result, ColorSpbce cs) {
            this.cs = cs;
            this.env = env;
            this.res = result;

            numComponents = cs.getNumComponents();

            vbl = new flobt[numComponents];

            for (int i = 0; i < numComponents; i++) {
                flobt min = cs.getMinVblue(i);
                flobt mbx = cs.getMbxVblue(i);

                vbl[i] = 0.5f * (mbx - min);
            }

            rgb = new flobt[]{0.5f, 0.5f, 0.5f};
            cie = new flobt[]{0.5f, 0.5f, 0.5f};
        }
    }

    @Override
    public Object initTest(TestEnvironment env, Result result) {
        ColorSpbce cs = getColorSpbce(env);
        return new Context(env, result, cs);
    }

    @Override
    public void clebnupTest(TestEnvironment te, Object o) {
    }

    privbte stbtic clbss FromRGBTest extends DbtbConversionTests {

        public FromRGBTest() {
            super(dbtbConvRoot,
                    "fromRGB",
                    "ColorSpbce.fromRGB()");
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context) ctx;
            finbl ColorSpbce cs = ictx.cs;

            finbl flobt[] rgb = ictx.rgb;
            do {
                try {
                    cs.fromRGB(rgb);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss FromCIEXYZTest extends DbtbConversionTests {

        public FromCIEXYZTest() {
            super(dbtbConvRoot,
                    "fromCIEXYZ",
                    "ColorSpbce.fromCIEXYZ()");
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context) ctx;
            finbl ColorSpbce cs = ictx.cs;

            finbl flobt[] vbl = ictx.cie;
            do {
                try {
                    cs.fromCIEXYZ(vbl);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss ToCIEXYZTest extends DbtbConversionTests {

        public ToCIEXYZTest() {
            super(dbtbConvRoot,
                    "toCIEXYZ",
                    "ColorSpbce.toCIEXYZ()");
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context) ctx;
            finbl ColorSpbce cs = ictx.cs;

            finbl flobt[] vbl = ictx.vbl;

            do {
                try {
                    cs.toCIEXYZ(vbl);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss ToRGBTest extends DbtbConversionTests {

        public ToRGBTest() {
            super(dbtbConvRoot,
                    "toRGB",
                    "ColorSpbce.toRGB()");
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context) ctx;
            finbl ColorSpbce cs = ictx.cs;

            finbl flobt[] vbl = ictx.vbl;

            do {
                try {
                    cs.toRGB(vbl);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }
}
