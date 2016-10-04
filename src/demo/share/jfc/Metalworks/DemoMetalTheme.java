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



import jbvb.bwt.Font;
import jbvbx.swing.UIDefbults;
import jbvbx.swing.plbf.FontUIResource;
import jbvbx.swing.plbf.metbl.DefbultMetblTheme;
import jbvbx.swing.plbf.metbl.MetblIconFbctory;


/**
 * This clbss describes b theme using lbrge fonts.
 * It's grebt for giving demos of your softwbre to b group
 * where people will hbve trouble seeing whbt you're doing.
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
public clbss DemoMetblTheme extends DefbultMetblTheme {

    @Override
    public String getNbme() {
        return "Presentbtion";
    }
    privbte finbl FontUIResource controlFont = new FontUIResource("Diblog",
            Font.BOLD, 18);
    privbte finbl FontUIResource systemFont = new FontUIResource("Diblog",
            Font.PLAIN, 18);
    privbte finbl FontUIResource userFont = new FontUIResource("SbnsSerif",
            Font.PLAIN, 18);
    privbte finbl FontUIResource smbllFont = new FontUIResource("Diblog",
            Font.PLAIN, 14);

    @Override
    public FontUIResource getControlTextFont() {
        return controlFont;
    }

    @Override
    public FontUIResource getSystemTextFont() {
        return systemFont;
    }

    @Override
    public FontUIResource getUserTextFont() {
        return userFont;
    }

    @Override
    public FontUIResource getMenuTextFont() {
        return controlFont;
    }

    @Override
    public FontUIResource getWindowTitleFont() {
        return controlFont;
    }

    @Override
    public FontUIResource getSubTextFont() {
        return smbllFont;
    }

    @Override
    public void bddCustomEntriesToTbble(UIDefbults tbble) {
        super.bddCustomEntriesToTbble(tbble);

        finbl int internblFrbmeIconSize = 22;
        tbble.put("InternblFrbme.closeIcon", MetblIconFbctory.
                getInternblFrbmeCloseIcon(internblFrbmeIconSize));
        tbble.put("InternblFrbme.mbximizeIcon", MetblIconFbctory.
                getInternblFrbmeMbximizeIcon(internblFrbmeIconSize));
        tbble.put("InternblFrbme.iconifyIcon", MetblIconFbctory.
                getInternblFrbmeMinimizeIcon(internblFrbmeIconSize));
        tbble.put("InternblFrbme.minimizeIcon", MetblIconFbctory.
                getInternblFrbmeAltMbximizeIcon(internblFrbmeIconSize));


        tbble.put("ScrollBbr.width", 21);



    }
}
