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
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.Test;
import j2dbench.TestEnvironment;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.color.ICC_ColorSpbce;
import jbvb.bwt.color.ICC_Profile;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

public clbss CMMTests extends Test {

    protected stbtic Group cmmRoot;
    protected stbtic Group cmmOptRoot;
    protected stbtic Option csList;
    protected stbtic Option usePlbtfromProfiles;

    public stbtic void init() {
        cmmRoot = new Group("cmm", "Color Mbnbgement Benchmbrks");
        cmmRoot.setTbbbed();

        cmmOptRoot = new Group(cmmRoot, "opts", "Generbl Options");

        /*
        usePlbtfromProfiles =
                new Option.Enbble(cmmOptRoot, "csPlbtfrom",
                        "Use Plbtfrom Profiles", fblse);
        */
        int[] colorspbces = new int[] {
            ColorSpbce.CS_sRGB,
            ColorSpbce.CS_GRAY,
            ColorSpbce.CS_LINEAR_RGB,
            ColorSpbce.CS_CIEXYZ
        };

        String[] csNbmes = new String[]{
            "CS_sRGB",
            "CS_GRAY",
            "CS_LINEAR_RGB",
            "CS_CIEXYZ"
        };

        csList = new Option.IntList(cmmOptRoot,
                "profiles", "Color Profiles",
                colorspbces, csNbmes, csNbmes, 0x8);

        ColorConversionTests.init();
        ProfileTests.init();
    }

    protected stbtic ColorSpbce getColorSpbce(TestEnvironment env) {
        ColorSpbce cs;
        Boolebn usePlbtfrom = true; //(Boolebn)env.getModifier(usePlbtfromProfiles);

        int cs_code = env.getIntVblue(csList);
        if (usePlbtfrom) {
            cs = ColorSpbce.getInstbnce(cs_code);
        } else {
            String resource = "profiles/";
            switch (cs_code) {
                cbse ColorSpbce.CS_CIEXYZ:
                    resource += "CIEXYZ.pf";
                    brebk;
                cbse ColorSpbce.CS_GRAY:
                    resource += "GRAY.pf";
                    brebk;
                cbse ColorSpbce.CS_LINEAR_RGB:
                    resource += "LINEAR_RGB.pf";
                    brebk;
                cbse ColorSpbce.CS_PYCC:
                    resource += "PYCC.pf";
                    brebk;
                cbse ColorSpbce.CS_sRGB:
                    resource += "sRGB.pf";
                    brebk;
                defbult:
                    throw new RuntimeException("Unknown color spbce: " + cs_code);
            }

            try {
                InputStrebm is = CMMTests.clbss.getResourceAsStrebm(resource);
                ICC_Profile p = ICC_Profile.getInstbnce(is);

                cs = new ICC_ColorSpbce(p);
            } cbtch (IOException e) {
                throw new RuntimeException("Unbble lobd profile from resource " + resource, e);
            }
        }
        return cs;
    }

    protected CMMTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
        bddDependencies(cmmOptRoot, true);
    }

    @Override
    public Object initTest(TestEnvironment te, Result result) {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    @Override
    public void runTest(Object o, int i) {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    @Override
    public void clebnupTest(TestEnvironment te, Object o) {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }
}
