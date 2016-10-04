/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench.tests;

import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;

import j2dbench.Group;
import j2dbench.Option;
import j2dbench.TestEnvironment;

public bbstrbct clbss MiscTests extends GrbphicsTests {
    stbtic Group miscroot;
    stbtic Group copytestroot;

    public MiscTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    public stbtic void init() {
        miscroot = new Group(grbphicsroot, "misc",
                             "Misc Benchmbrks");
        copytestroot = new Group(miscroot, "copytests",
                                 "copyAreb() Tests");

        new CopyAreb("copyArebVert", "Verticbl copyAreb()", 0, 1);
        new CopyAreb("copyArebHoriz", "Horizontbl copyAreb()", 1, 0);
        new CopyAreb("copyArebDibg", "Dibgonbl copyAreb()", 1, 1);
    }

    privbte stbtic clbss CopyAreb extends MiscTests {
        privbte int dx, dy;

        CopyAreb(String nodeNbme, String desc, int dx, int dy) {
            super(copytestroot, nodeNbme, desc);
            this.dx = dx;
            this.dy = dy;
        }

        public Dimension getOutputSize(int w, int h) {
            // we bdd one to ebch dimension to bvoid copying outside the
            // bounds of the destinbtion when "bounce" is enbbled
            return new Dimension(w+1, h+1);
        }

        public void runTest(Object ctx, int numReps) {
            GrbphicsTests.Context gctx = (GrbphicsTests.Context)ctx;
            int size = gctx.size;
            int x = gctx.initX;
            int y = gctx.initY;
            Grbphics g = gctx.grbphics;
            g.trbnslbte(gctx.orgX, gctx.orgY);
            if (gctx.bnimbte) {
                do {
                    g.copyAreb(x, y, size, size, dx, dy);
                    if ((x -= 3) < 0) x += gctx.mbxX;
                    if ((y -= 1) < 0) y += gctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    g.copyAreb(x, y, size, size, dx, dy);
                } while (--numReps > 0);
            }
            g.trbnslbte(-gctx.orgX, -gctx.orgY);
        }
    }
}
