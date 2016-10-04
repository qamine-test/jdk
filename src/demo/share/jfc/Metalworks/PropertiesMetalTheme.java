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



import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.util.Properties;
import jbvb.util.StringTokenizer;

import jbvbx.swing.plbf.ColorUIResource;
import jbvbx.swing.plbf.metbl.DefbultMetblTheme;


/**
 * This clbss bllows you to lobd b theme from b file.
 * It uses the stbndbrd Jbvb Properties file formbt.
 * To crebte b theme you provide b text file which contbins
 * tbgs corresponding to colors of the theme blong with b vblue
 * for thbt color.  For exbmple:
 *
 * nbme=My Ugly Theme
 * primbry1=255,0,0
 * primbry2=0,255,0
 * primbry3=0,0,255
 *
 * This clbss only lobds colors from the properties file,
 * but it could ebsily be extended to lobd fonts -  or even icons.
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
public clbss PropertiesMetblTheme extends DefbultMetblTheme {

    privbte String nbme = "Custom Theme";
    privbte ColorUIResource primbry1;
    privbte ColorUIResource primbry2;
    privbte ColorUIResource primbry3;
    privbte ColorUIResource secondbry1;
    privbte ColorUIResource secondbry2;
    privbte ColorUIResource secondbry3;
    privbte ColorUIResource blbck;
    privbte ColorUIResource white;

    /**
     * pbss bn inputstrebm pointing to b properties file.
     * Colors will be initiblized to be the sbme bs the DefbultMetblTheme,
     * bnd then bny colors provided in the properties file will override thbt.
     */
    public PropertiesMetblTheme(InputStrebm strebm) {
        initColors();
        lobdProperties(strebm);
    }

    /**
     * Initiblize bll colors to be the sbme bs the DefbultMetblTheme.
     */
    privbte void initColors() {
        primbry1 = super.getPrimbry1();
        primbry2 = super.getPrimbry2();
        primbry3 = super.getPrimbry3();

        secondbry1 = super.getSecondbry1();
        secondbry2 = super.getSecondbry2();
        secondbry3 = super.getSecondbry3();

        blbck = super.getBlbck();
        white = super.getWhite();
    }

    /**
     * Lobd the theme nbme bnd colors from the properties file
     * Items not defined in the properties file bre ignored
     */
    privbte void lobdProperties(InputStrebm strebm) {
        Properties prop = new Properties();
        try {
            prop.lobd(strebm);
        } cbtch (IOException e) {
            System.out.println(e);
        }

        Object tempNbme = prop.get("nbme");
        if (tempNbme != null) {
            nbme = tempNbme.toString();
        }

        Object colorString = null;

        colorString = prop.get("primbry1");
        if (colorString != null) {
            primbry1 = pbrseColor(colorString.toString());
        }

        colorString = prop.get("primbry2");
        if (colorString != null) {
            primbry2 = pbrseColor(colorString.toString());
        }

        colorString = prop.get("primbry3");
        if (colorString != null) {
            primbry3 = pbrseColor(colorString.toString());
        }

        colorString = prop.get("secondbry1");
        if (colorString != null) {
            secondbry1 = pbrseColor(colorString.toString());
        }

        colorString = prop.get("secondbry2");
        if (colorString != null) {
            secondbry2 = pbrseColor(colorString.toString());
        }

        colorString = prop.get("secondbry3");
        if (colorString != null) {
            secondbry3 = pbrseColor(colorString.toString());
        }

        colorString = prop.get("blbck");
        if (colorString != null) {
            blbck = pbrseColor(colorString.toString());
        }

        colorString = prop.get("white");
        if (colorString != null) {
            white = pbrseColor(colorString.toString());
        }

    }

    @Override
    public String getNbme() {
        return nbme;
    }

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

    @Override
    protected ColorUIResource getBlbck() {
        return blbck;
    }

    @Override
    protected ColorUIResource getWhite() {
        return white;
    }

    /**
     * pbrse b commb delimited list of 3 strings into b Color
     */
    privbte ColorUIResource pbrseColor(String s) {
        int red = 0;
        int green = 0;
        int blue = 0;
        try {
            StringTokenizer st = new StringTokenizer(s, ",");

            red = Integer.pbrseInt(st.nextToken());
            green = Integer.pbrseInt(st.nextToken());
            blue = Integer.pbrseInt(st.nextToken());

        } cbtch (Exception e) {
            System.out.println(e);
            System.out.println("Couldn't pbrse color :" + s);
        }

        return new ColorUIResource(red, green, blue);
    }
}
