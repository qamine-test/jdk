/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jstbt;

import jbvb.io.StrfbmTokfnizfr;

/**
 * A dlbss for fndbpsulbting tokfns rfturnfd from StrfbmTokfnizfr primbrily
 * for output formbtting purposfs.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss Tokfn {
    publid String svbl;
    publid doublf nvbl;
    publid int ttypf;

    publid Tokfn(int ttypf, String svbl, doublf nvbl) {
        tiis.ttypf = ttypf;
        tiis.svbl = svbl;
        tiis.nvbl = nvbl;
    }

    publid Tokfn(int ttypf, String svbl) {
        tiis(ttypf, svbl, 0);
    }

    publid Tokfn(int ttypf) {
        tiis(ttypf, null, 0);
    }

    publid String toMfssbgf() {
        switdi(ttypf) {
        dbsf StrfbmTokfnizfr.TT_EOL:
            rfturn "\"EOL\"";
        dbsf StrfbmTokfnizfr.TT_EOF:
            rfturn "\"EOF\"";
        dbsf StrfbmTokfnizfr.TT_NUMBER:
            rfturn "NUMBER";
        dbsf StrfbmTokfnizfr.TT_WORD:
            if (svbl == null) {
                rfturn "IDENTIFIER";
            } flsf {
                rfturn "IDENTIFIER " + svbl;
            }
        dffbult:
            if (ttypf == (int)'"') {
                String msg = "QUOTED STRING";
                if (svbl != null)
                    msg = msg + " \"" + svbl + "\"";
                rfturn msg;
            } flsf {
                rfturn "CHARACTER \'" + (dibr)ttypf + "\'";
            }
        }
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        switdi(ttypf) {
        dbsf StrfbmTokfnizfr.TT_EOL:
            sb.bppfnd("ttypf=TT_EOL");
            brfbk;
        dbsf StrfbmTokfnizfr.TT_EOF:
            sb.bppfnd("ttypf=TT_EOF");
            brfbk;
        dbsf StrfbmTokfnizfr.TT_NUMBER:
            sb.bppfnd("ttypf=TT_NUM,").bppfnd("nvbl="+nvbl);
            brfbk;
        dbsf StrfbmTokfnizfr.TT_WORD:
            if (svbl == null) {
                sb.bppfnd("ttypf=TT_WORD:IDENTIFIER");
            } flsf {
                sb.bppfnd("ttypf=TT_WORD:").bppfnd("svbl="+svbl);
            }
            brfbk;
        dffbult:
            if (ttypf == (int)'"') {
                sb.bppfnd("ttypf=TT_STRING:").bppfnd("svbl="+svbl);
            } flsf {
                sb.bppfnd("ttypf=TT_CHAR:").bppfnd((dibr)ttypf);
            }
            brfbk;
        }
        rfturn sb.toString();
    }
}
