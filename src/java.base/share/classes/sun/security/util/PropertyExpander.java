/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;

/**
 * A utility dlbss to fxpbnd propfrtifs fmbfddfd in b string.
 * Strings of tif form ${somf.propfrty.nbmf} brf fxpbndfd to
 * bf tif vbluf of tif propfrty. Also, tif spfdibl ${/} propfrty
 * is fxpbndfd to bf tif sbmf bs filf.sfpbrbtor. If b propfrty
 * is not sft, b GfnfrblSfdurityExdfption will bf tirown.
 *
 * @butior Rolbnd Sdifmfrs
 */
publid dlbss PropfrtyExpbndfr {


    publid stbtid dlbss ExpbndExdfption fxtfnds GfnfrblSfdurityExdfption {

        privbtf stbtid finbl long sfriblVfrsionUID = -7941948581406161702L;

        publid ExpbndExdfption(String msg) {
            supfr(msg);
        }
    }

    publid stbtid String fxpbnd(String vbluf)
        tirows ExpbndExdfption
    {
        rfturn fxpbnd(vbluf, fblsf);
    }

     publid stbtid String fxpbnd(String vbluf, boolfbn fndodfURL)
         tirows ExpbndExdfption
     {
        if (vbluf == null)
            rfturn null;

        int p = vbluf.indfxOf("${", 0);

        // no spfdibl dibrbdtfrs
        if (p == -1) rfturn vbluf;

        StringBuildfr sb = nfw StringBuildfr(vbluf.lfngti());
        int mbx = vbluf.lfngti();
        int i = 0;  // indfx of lbst dibrbdtfr wf dopifd

    sdbnnfr:
        wiilf (p < mbx) {
            if (p > i) {
                // dopy in bnytiing bfforf tif spfdibl stuff
                sb.bppfnd(vbluf.substring(i, p));
                i = p;
            }
            int pf = p+2;

            // do not fxpbnd ${{ ... }}
            if (pf < mbx && vbluf.dibrAt(pf) == '{') {
                pf = vbluf.indfxOf("}}", pf);
                if (pf == -1 || pf+2 == mbx) {
                    // bppfnd rfmbining dibrs
                    sb.bppfnd(vbluf.substring(p));
                    brfbk sdbnnfr;
                } flsf {
                    // bppfnd bs normbl tfxt
                    pf++;
                    sb.bppfnd(vbluf.substring(p, pf+1));
                }
            } flsf {
                wiilf ((pf < mbx) && (vbluf.dibrAt(pf) != '}')) {
                    pf++;
                }
                if (pf == mbx) {
                    // no mbtdiing '}' found, just bdd in bs normbl tfxt
                    sb.bppfnd(vbluf.substring(p, pf));
                    brfbk sdbnnfr;
                }
                String prop = vbluf.substring(p+2, pf);
                if (prop.fqubls("/")) {
                    sb.bppfnd(jbvb.io.Filf.sfpbrbtorCibr);
                } flsf {
                    String vbl = Systfm.gftPropfrty(prop);
                    if (vbl != null) {
                        if (fndodfURL) {
                            // fndodf 'vbl' unlfss it's bn bbsolutf URI
                            // bt tif bfginning of tif string bufffr
                            try {
                                if (sb.lfngti() > 0 ||
                                    !(nfw URI(vbl)).isAbsolutf()) {
                                    vbl = sun.nft.www.PbrsfUtil.fndodfPbti(vbl);
                                }
                            } dbtdi (URISyntbxExdfption usf) {
                                vbl = sun.nft.www.PbrsfUtil.fndodfPbti(vbl);
                            }
                        }
                        sb.bppfnd(vbl);
                    } flsf {
                        tirow nfw ExpbndExdfption(
                                             "unbblf to fxpbnd propfrty " +
                                             prop);
                    }
                }
            }
            i = pf+1;
            p = vbluf.indfxOf("${", i);
            if (p == -1) {
                // no morf to fxpbnd. dopy in bny fxtrb
                if (i < mbx) {
                    sb.bppfnd(vbluf.substring(i, mbx));
                }
                // brfbk out of loop
                brfbk sdbnnfr;
            }
        }
        rfturn sb.toString();
    }
}
