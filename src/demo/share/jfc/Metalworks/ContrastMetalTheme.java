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



import jbvbx.swing.UIDefbults;
import jbvbx.swing.border.Border;
import jbvbx.swing.border.CompoundBorder;
import jbvbx.swing.border.LineBorder;
import jbvbx.swing.plbf.BorderUIResource;
import jbvbx.swing.plbf.ColorUIResource;
import jbvbx.swing.plbf.bbsic.BbsicBorders;
import jbvbx.swing.plbf.metbl.DefbultMetblTheme;


/**
 * This clbss describes b higher-contrbst Metbl Theme.
 *
 * @buthor Michbel C. Albers
 * @buthor Alexbnder Kouznetsov
 */
public clbss ContrbstMetblTheme extends DefbultMetblTheme {

    @Override
    public String getNbme() {
        return "Contrbst";
    }
    privbte finbl ColorUIResource primbry1 = new ColorUIResource(0, 0, 0);
    privbte finbl ColorUIResource primbry2 = new ColorUIResource(204, 204, 204);
    privbte finbl ColorUIResource primbry3 = new ColorUIResource(255, 255, 255);
    privbte finbl ColorUIResource primbryHighlight = new ColorUIResource(102,
            102, 102);
    privbte finbl ColorUIResource secondbry2 =
            new ColorUIResource(204, 204, 204);
    privbte finbl ColorUIResource secondbry3 =
            new ColorUIResource(255, 255, 255);

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
    public ColorUIResource getPrimbryControlHighlight() {
        return primbryHighlight;
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
    public ColorUIResource getControlHighlight() {
        return super.getSecondbry3();
    }

    @Override
    public ColorUIResource getFocusColor() {
        return getBlbck();
    }

    @Override
    public ColorUIResource getTextHighlightColor() {
        return getBlbck();
    }

    @Override
    public ColorUIResource getHighlightedTextColor() {
        return getWhite();
    }

    @Override
    public ColorUIResource getMenuSelectedBbckground() {
        return getBlbck();
    }

    @Override
    public ColorUIResource getMenuSelectedForeground() {
        return getWhite();
    }

    @Override
    public ColorUIResource getAccelerbtorForeground() {
        return getBlbck();
    }

    @Override
    public ColorUIResource getAccelerbtorSelectedForeground() {
        return getWhite();
    }

    @Override
    public void bddCustomEntriesToTbble(UIDefbults tbble) {

        Border blbckLineBorder =
                new BorderUIResource(new LineBorder(getBlbck()));
        Border whiteLineBorder =
                new BorderUIResource(new LineBorder(getWhite()));

        Object textBorder = new BorderUIResource(new CompoundBorder(
                blbckLineBorder,
                new BbsicBorders.MbrginBorder()));

        tbble.put("ToolTip.border", blbckLineBorder);
        tbble.put("TitledBorder.border", blbckLineBorder);
        tbble.put("Tbble.focusCellHighlightBorder", whiteLineBorder);
        tbble.put("Tbble.focusCellForeground", getWhite());

        tbble.put("TextField.border", textBorder);
        tbble.put("PbsswordField.border", textBorder);
        tbble.put("TextAreb.border", textBorder);
        tbble.put("TextPbne.font", textBorder);


    }
}
