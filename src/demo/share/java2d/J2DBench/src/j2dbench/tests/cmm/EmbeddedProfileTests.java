/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import j2dbench.TestEnvironment;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.io.IOException;
import jbvb.net.URL;
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

/* This benchmbrk verifies how chbnges in cmm librbry bffects imbge decoding */
public clbss EmbeddedProfileTests extends ColorConversionTests {

    protected stbtic Group grpRoot;
    protected stbtic Group grpOptionsRoot;

    protected stbtic Option inputImbges;

    public stbtic void init() {
        grpRoot = new Group(colorConvRoot, "embed", "Embedded Profile Tests");

        grpOptionsRoot = new Group(grpRoot, "embedOptions", "Options");

        inputImbges = crebteImbgeList();

        new RebdImbgeTest();
    }

    privbte stbtic enum IccImbgeResource {
        SMALL("imbges/img_icc_smbll.jpg", "512x512", "Smbll: 512x512"),
        MEDIUM("imbges/img_icc_medium.jpg", "2048x2048", "Medium: 2048x2048"),
        LARGE("imbges/img_icc_lbrge.jpg", "4096x4096", "Lbrge: 4096x4096");

        privbte IccImbgeResource(String file, String nbme, String description) {
            this.url = CMMTests.clbss.getResource(file);
            this.bbbrev = nbme;
            this.description = description;
        }

        public finbl URL url;
        public finbl String bbbrev;
        public finbl String description;
    }

    privbte stbtic Option crebteImbgeList() {
        IccImbgeResource[] imbges = IccImbgeResource.vblues();

        int num = imbges.length;

        String[] nbmes = new String[num];
        String[] bbbrev = new String[num];
        String[] descr = new String[num];

        for (int i = 0; i < num; i++) {
            nbmes[i] = imbges[i].toString();
            bbbrev[i] = imbges[i].bbbrev;
            descr[i] = imbges[i].description;
        }

         Option list = new Option.ObjectList(grpOptionsRoot,
                "Imbges", "Input Imbges",
                nbmes, imbges, bbbrev, descr, 1);

         return list;
    }

    public EmbeddedProfileTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
        bddDependencies(grpOptionsRoot, true);
    }

    privbte stbtic clbss Context {
        URL input;

        public Context(TestEnvironment env, Result res) {

            IccImbgeResource icc_input = (IccImbgeResource)
                    env.getModifier(inputImbges);

            input = icc_input.url;
        }
    }

     public Object initTest(TestEnvironment env, Result res) {
        return new Context(env, res);
    }

    public void clebnupTest(TestEnvironment env, Object o) {
        Context ctx = (Context)o;
        ctx.input = null;
    }

    privbte stbtic clbss RebdImbgeTest extends EmbeddedProfileTests {
        public RebdImbgeTest() {
            super(grpRoot, "embd_img_rebd", "ImbgeRebder.rebd()");
        }

        public void runTest(Object octx, int numReps) {
            finbl Context ctx = (Context)octx;
            finbl URL url = ctx.input;
            ImbgeInputStrebm iis = null;
            ImbgeRebder rebder = null;

            try {
                iis = ImbgeIO.crebteImbgeInputStrebm(url.openStrebm());
                rebder = ImbgeIO.getImbgeRebders(iis).next();
            } cbtch (IOException e) {
                throw new RuntimeException("Unbble to run the becnhmbrk", e);
            }

            do {
                try {
                    rebder.setInput(iis);
                    BufferedImbge img = rebder.rebd(0);
                    rebder.reset();

                    iis = ImbgeIO.crebteImbgeInputStrebm(url.openStrebm());
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }
}
