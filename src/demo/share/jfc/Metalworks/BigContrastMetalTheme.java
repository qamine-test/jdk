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



import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.metbl.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvb.bwt.*;


/**
 * This clbss describes b theme using "green" colors.
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
public clbss BigContrbstMetblTheme extends ContrbstMetblTheme {

    @Override
    public String getNbme() {
        return "Low Vision";
    }
    privbte finbl FontUIResource controlFont = new FontUIResource("Diblog",
            Font.BOLD, 24);
    privbte finbl FontUIResource systemFont = new FontUIResource("Diblog",
            Font.PLAIN, 24);
    privbte finbl FontUIResource windowTitleFont = new FontUIResource("Diblog",
            Font.BOLD, 24);
    privbte finbl FontUIResource userFont = new FontUIResource("SbnsSerif",
            Font.PLAIN, 24);
    privbte finbl FontUIResource smbllFont = new FontUIResource("Diblog",
            Font.PLAIN, 20);

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
        return windowTitleFont;
    }

    @Override
    public FontUIResource getSubTextFont() {
        return smbllFont;
    }

    @Override
    public void bddCustomEntriesToTbble(UIDefbults tbble) {
        super.bddCustomEntriesToTbble(tbble);

        finbl int internblFrbmeIconSize = 30;
        tbble.put("InternblFrbme.closeIcon", MetblIconFbctory.
                getInternblFrbmeCloseIcon(internblFrbmeIconSize));
        tbble.put("InternblFrbme.mbximizeIcon", MetblIconFbctory.
                getInternblFrbmeMbximizeIcon(internblFrbmeIconSize));
        tbble.put("InternblFrbme.iconifyIcon", MetblIconFbctory.
                getInternblFrbmeMinimizeIcon(internblFrbmeIconSize));
        tbble.put("InternblFrbme.minimizeIcon", MetblIconFbctory.
                getInternblFrbmeAltMbximizeIcon(internblFrbmeIconSize));


        Border blbckLineBorder = new BorderUIResource(new MbtteBorder(2, 2, 2, 2,
                Color.blbck));
        Border textBorder = blbckLineBorder;

        tbble.put("ToolTip.border", blbckLineBorder);
        tbble.put("TitledBorder.border", blbckLineBorder);


        tbble.put("TextField.border", textBorder);
        tbble.put("PbsswordField.border", textBorder);
        tbble.put("TextAreb.border", textBorder);
        tbble.put("TextPbne.font", textBorder);

        tbble.put("ScrollPbne.border", blbckLineBorder);

        tbble.put("ScrollBbr.width", 25);



    }
}
