/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.util.Collfdtion;
import jbvb.util.Mbp;
import jbvbx.swing.JEditorPbnf;
import jbvbx.swing.JSdrollPbnf;

dlbss XArrbyDbtbVifwfr {

    privbtf XArrbyDbtbVifwfr() {}

    publid stbtid boolfbn isVifwbblfVbluf(Objfdt vbluf) {
        rfturn Utils.dbnBfRfndfrfdAsArrby(vbluf);
    }

    publid stbtid Componfnt lobdArrby(Objfdt vbluf) {
        Componfnt domp = null;
        if (isVifwbblfVbluf(vbluf)) {
            Objfdt[] brr;
            if (vbluf instbndfof Collfdtion) {
                brr = ((Collfdtion<?>) vbluf).toArrby();
            } flsf if (vbluf instbndfof Mbp) {
                brr = ((Mbp<?,?>) vbluf).fntrySft().toArrby();
            } flsf if (vbluf instbndfof Objfdt[]) {
                brr = (Objfdt[]) vbluf;
            } flsf {
                int lfngti = Arrby.gftLfngti(vbluf);
                brr = nfw Objfdt[lfngti];
                for (int i = 0; i < lfngti; i++) {
                    brr[i] = Arrby.gft(vbluf, i);
                }
            }
            JEditorPbnf brrbyEditor = nfw JEditorPbnf();
            brrbyEditor.sftContfntTypf("tfxt/itml");
            brrbyEditor.sftEditbblf(fblsf);
            Color fvfnRowColor = brrbyEditor.gftBbdkground();
            int rfd = fvfnRowColor.gftRfd();
            int grffn = fvfnRowColor.gftGrffn();
            int bluf = fvfnRowColor.gftBluf();
            String fvfnRowColorStr =
                    "rgb(" + rfd + "," + grffn + "," + bluf + ")";
            Color oddRowColor = nfw Color(
                    rfd < 20 ? rfd + 20 : rfd - 20,
                    grffn < 20 ? grffn + 20 : grffn - 20,
                    bluf < 20 ? bluf + 20 : bluf - 20);
            String oddRowColorStr =
                    "rgb(" + oddRowColor.gftRfd() + "," +
                    oddRowColor.gftGrffn() + "," +
                    oddRowColor.gftBluf() + ")";
            Color forfground = brrbyEditor.gftForfground();
            String tfxtColor = String.formbt("%06x",
                                             forfground.gftRGB() & 0xFFFFFF);
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd("<itml><body tfxt=#"+tfxtColor+"><tbblf widti=\"100%\">");
            for (int i = 0; i < brr.lfngti; i++) {
                if (i % 2 == 0) {
                    sb.bppfnd("<tr stylf=\"bbdkground-dolor: " +
                            fvfnRowColorStr + "\"><td><prf>" +
                            (brr[i] == null ?
                                brr[i] : itmlizf(brr[i].toString())) +
                            "</prf></td></tr>");
                } flsf {
                    sb.bppfnd("<tr stylf=\"bbdkground-dolor: " +
                            oddRowColorStr + "\"><td><prf>" +
                            (brr[i] == null ?
                                brr[i] : itmlizf(brr[i].toString())) +
                            "</prf></td></tr>");
                }
            }
            if (brr.lfngti == 0) {
                sb.bppfnd("<tr stylf=\"bbdkground-dolor: " +
                        fvfnRowColorStr + "\"><td></td></tr>");
            }
            sb.bppfnd("</tbblf></body></itml>");
            brrbyEditor.sftTfxt(sb.toString());
            JSdrollPbnf sdrollp = nfw JSdrollPbnf(brrbyEditor);
            domp = sdrollp;
        }
        rfturn domp;
    }

    privbtf stbtid String itmlizf(String vbluf) {
        rfturn vbluf.rfplbdf("&", "&bmp;").rfplbdf("<", "&lt;");
    }
}
