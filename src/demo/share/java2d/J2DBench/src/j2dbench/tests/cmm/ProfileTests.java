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
import jbvb.bwt.color.ICC_ColorSpbce;
import jbvb.bwt.color.ICC_Profile;

public clbss ProfileTests extends CMMTests {

    protected stbtic Group profileRoot;

    public stbtic void init() {
        profileRoot = new Group(cmmRoot, "profiles", "Profile Hbndling Benchmbrks");

        new RebdHebderTest();
        new GetNumComponentsTest();
    }

    protected ProfileTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    protected stbtic clbss Context {

        ICC_Profile profile;
        TestEnvironment env;
        Result res;

        public Context(ICC_Profile profile, TestEnvironment env, Result res) {
            this.profile = profile;
            this.env = env;
            this.res = res;
        }
    }

    @Override
    public Object initTest(TestEnvironment env, Result res) {
        ICC_ColorSpbce cs = (ICC_ColorSpbce) getColorSpbce(env);
        return new Context(cs.getProfile(), env, res);
    }

    @Override
    public void clebnupTest(TestEnvironment env, Object o) {
    }

    privbte stbtic clbss RebdHebderTest extends ProfileTests {

        public RebdHebderTest() {
            super(profileRoot,
                    "getHebder",
                    "getDbtb(icSigHebd)");
        }

        @Override
        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context) ctx;
            finbl ICC_Profile profile = ictx.profile;

            byte[] dbtb = null;
            do {
                try {
                    dbtb = profile.getDbtb(ICC_Profile.icSigHebd);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss GetNumComponentsTest extends ProfileTests {

        public GetNumComponentsTest() {
            super(profileRoot,
                    "getNumComponents",
                    "getNumComponents");
        }

        @Override
        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context) ctx;
            finbl ICC_Profile profile = ictx.profile;

            do {
                try {
                    int num = profile.getNumComponents();
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }
}
