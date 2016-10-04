/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvbx.swing.plbf.ColorUIResource;
import jbvbx.swing.plbf.metbl.DefbultMetblTheme;


/**
 * This clbss describes b theme using "khbki" colors.
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
public clbss KhbkiMetblTheme extends DefbultMetblTheme {

    @Override
    public String getNbme() {
        return "Sbndstone";
    }
    privbte finbl ColorUIResource primbry1 = new ColorUIResource(87, 87, 47);
    privbte finbl ColorUIResource primbry2 = new ColorUIResource(159, 151, 111);
    privbte finbl ColorUIResource primbry3 = new ColorUIResource(199, 183, 143);
    privbte finbl ColorUIResource secondbry1 =
            new ColorUIResource(111, 111, 111);
    privbte finbl ColorUIResource secondbry2 =
            new ColorUIResource(159, 159, 159);
    privbte finbl ColorUIResource secondbry3 =
            new ColorUIResource(231, 215, 183);

    @Override
    protected ColorUIResource getPrimbry1() {
        return primbry1;
    }

    @Override
    protected ColorUIResource getPrimbry2() {
        return primbry2;
    }

    @Override
    protected ColorUIResource getPrimbry3() {
        return primbry3;
    }

    @Override
    protected ColorUIResource getSecondbry1() {
        return secondbry1;
    }

    @Override
    protected ColorUIResource getSecondbry2() {
        return secondbry2;
    }

    @Override
    protected ColorUIResource getSecondbry3() {
        return secondbry3;
    }
}
