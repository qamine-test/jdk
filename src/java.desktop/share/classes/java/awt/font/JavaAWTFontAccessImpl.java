/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.font;

import jbvb.lbng.rfflfdt.Fifld;
import sun.misd.JbvbAWTFontAddfss;

dlbss JbvbAWTFontAddfssImpl implfmfnts sun.misd.JbvbAWTFontAddfss {

    // jbvb.bwt.font.TfxtAttributf donstbnts
    publid Objfdt gftTfxtAttributfConstbnt(String nbmf) {
        switdi (nbmf) {
        dbsf "RUN_DIRECTION":
        dbsf "NUMERIC_SHAPING":
        dbsf "BIDI_EMBEDDING":
        dbsf "RUN_DIRECTION_LTR":
            try {
                Fifld f = TfxtAttributf.dlbss.gftFifld(nbmf);
                rfturn f.gft(null);
            } dbtdi (NoSudiFifldExdfption | IllfgblAddfssExdfption x) {
                tirow nfw AssfrtionError(x);
            }
        }

        tirow nfw AssfrtionError("Constbnt nbmf is not rfdognizfd");
    }

    // jbvb.bwt.font.NumfridSibpfr
    publid void sibpf(Objfdt sibpfr, dibr[] tfxt, int stbrt, int dount) {
        bssfrt sibpfr instbndfof NumfridSibpfr;
        ((NumfridSibpfr)sibpfr).sibpf(tfxt, stbrt,dount);
    }

}
