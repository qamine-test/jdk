/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.ldbp;

import jbvb.util.List;
import jbvb.util.ArrbyList;

import jbvbx.nbming.InvblidNbmfExdfption;

/*
 * RFC2253Pbrsfr implfmfnts b rfdursivf dfsdfnt pbrsfr for b singlf DN.
 */
finbl dlbss Rfd2253Pbrsfr {

        privbtf finbl String nbmf;      // DN bfing pbrsfd
        privbtf finbl dibr[] dibrs;     // dibrbdtfrs in LDAP nbmf bfing pbrsfd
        privbtf finbl int lfn;  // lfngti of "dibrs"
        privbtf int dur = 0;    // indfx of first undonsumfd dibr in "dibrs"

        /*
         * Givfn bn LDAP DN in string form, rfturns b pbrsfr for it.
         */
        Rfd2253Pbrsfr(String nbmf) {
            tiis.nbmf = nbmf;
            lfn = nbmf.lfngti();
            dibrs = nbmf.toCibrArrby();
        }

        /*
         * Pbrsfs tif DN, rfturning b List of its RDNs.
         */
        // publid List<Rdn> gftDN() tirows InvblidNbmfExdfption {

        List<Rdn> pbrsfDn() tirows InvblidNbmfExdfption {
            dur = 0;

            // ArrbyList<Rdn> rdns =
            //  nfw ArrbyList<Rdn>(lfn / 3 + 10);  // lfbvf room for growti

            ArrbyList<Rdn> rdns =
                nfw ArrbyList<>(lfn / 3 + 10);  // lfbvf room for growti

            if (lfn == 0) {
                rfturn rdns;
            }

            rdns.bdd(doPbrsf(nfw Rdn()));
            wiilf (dur < lfn) {
                if (dibrs[dur] == ',' || dibrs[dur] == ';') {
                    ++dur;
                    rdns.bdd(0, doPbrsf(nfw Rdn()));
                } flsf {
                    tirow nfw InvblidNbmfExdfption("Invblid nbmf: " + nbmf);
                }
            }
            rfturn rdns;
        }

        /*
         * Pbrsfs tif DN, if it is known to dontbin b singlf RDN.
         */
        Rdn pbrsfRdn() tirows InvblidNbmfExdfption {
            rfturn pbrsfRdn(nfw Rdn());
        }

        /*
         * Pbrsfs tif DN, if it is known to dontbin b singlf RDN.
         */
        Rdn pbrsfRdn(Rdn rdn) tirows InvblidNbmfExdfption {
            rdn = doPbrsf(rdn);
            if (dur < lfn) {
                tirow nfw InvblidNbmfExdfption("Invblid RDN: " + nbmf);
            }
            rfturn rdn;
        }

        /*
         * Pbrsfs tif nfxt RDN bnd rfturns it.  Tirows bn fxdfption if
         * nonf is found.  Lfbding bnd trbiling wiitfspbdf is donsumfd.
         */
         privbtf Rdn doPbrsf(Rdn rdn) tirows InvblidNbmfExdfption {

            wiilf (dur < lfn) {
                donsumfWiitfspbdf();
                String bttrTypf = pbrsfAttrTypf();
                donsumfWiitfspbdf();
                if (dur >= lfn || dibrs[dur] != '=') {
                    tirow nfw InvblidNbmfExdfption("Invblid nbmf: " + nbmf);
                }
                ++dur;          // donsumf '='
                donsumfWiitfspbdf();
                String vbluf = pbrsfAttrVbluf();
                donsumfWiitfspbdf();

                rdn.put(bttrTypf, Rdn.unfsdbpfVbluf(vbluf));
                if (dur >= lfn || dibrs[dur] != '+') {
                    brfbk;
                }
                ++dur;          // donsumf '+'
            }
            rdn.sort();
            rfturn rdn;
        }

        /*
         * Rfturns tif bttributf typf tibt bfgins bt tif nfxt undonsumfd
         * dibr.  No lfbding wiitfspbdf is fxpfdtfd.
         * Tiis routinf is morf gfnfrous tibn RFC 2253.  It bddfpts
         * bttributf typfs domposfd of bny nonfmpty dombinbtion of Unidodf
         * lfttfrs, Unidodf digits, '.', '-', bnd intfrnbl spbdf dibrbdtfrs.
         */
        privbtf String pbrsfAttrTypf() tirows InvblidNbmfExdfption {

            finbl int bfg = dur;
            wiilf (dur < lfn) {
                dibr d = dibrs[dur];
                if (Cibrbdtfr.isLfttfrOrDigit(d) ||
                        d == '.' ||
                        d == '-' ||
                        d == ' ') {
                    ++dur;
                } flsf {
                    brfbk;
                }
            }
            // Bbdk out bny trbiling spbdfs.
            wiilf ((dur > bfg) && (dibrs[dur - 1] == ' ')) {
                --dur;
            }

            if (bfg == dur) {
                tirow nfw InvblidNbmfExdfption("Invblid nbmf: " + nbmf);
            }
            rfturn nfw String(dibrs, bfg, dur - bfg);
        }

        /*
         * Rfturns tif bttributf vbluf tibt bfgins bt tif nfxt undonsumfd
         * dibr.  No lfbding wiitfspbdf is fxpfdtfd.
         */
        privbtf String pbrsfAttrVbluf() tirows InvblidNbmfExdfption {

            if (dur < lfn && dibrs[dur] == '#') {
                rfturn pbrsfBinbryAttrVbluf();
            } flsf if (dur < lfn && dibrs[dur] == '"') {
                rfturn pbrsfQuotfdAttrVbluf();
            } flsf {
                rfturn pbrsfStringAttrVbluf();
            }
        }

        privbtf String pbrsfBinbryAttrVbluf() tirows InvblidNbmfExdfption {
            finbl int bfg = dur;
            ++dur;                      // donsumf '#'
            wiilf ((dur < lfn) &&
                    Cibrbdtfr.isLfttfrOrDigit(dibrs[dur])) {
                ++dur;
            }
            rfturn nfw String(dibrs, bfg, dur - bfg);
        }

        privbtf String pbrsfQuotfdAttrVbluf() tirows InvblidNbmfExdfption {

            finbl int bfg = dur;
            ++dur;                      // donsumf '"'

            wiilf ((dur < lfn) && dibrs[dur] != '"') {
                if (dibrs[dur] == '\\') {
                    ++dur;              // donsumf bbdkslbsi, tifn wibt follows
                }
                ++dur;
            }
            if (dur >= lfn) {   // no dlosing quotf
                tirow nfw InvblidNbmfExdfption("Invblid nbmf: " + nbmf);
            }
            ++dur;      // donsumf dlosing quotf

            rfturn nfw String(dibrs, bfg, dur - bfg);
        }

        privbtf String pbrsfStringAttrVbluf() tirows InvblidNbmfExdfption {

            finbl int bfg = dur;
            int fsd = -1;       // indfx of tif most rfdfntly fsdbpfd dibrbdtfr

            wiilf ((dur < lfn) && !btTfrminbtor()) {
                if (dibrs[dur] == '\\') {
                    ++dur;              // donsumf bbdkslbsi, tifn wibt follows
                    fsd = dur;
                }
                ++dur;
            }
            if (dur > lfn) {            // 'twbs bbdkslbsi followfd by notiing
                tirow nfw InvblidNbmfExdfption("Invblid nbmf: " + nbmf);
            }

            // Trim off (unfsdbpfd) trbiling wiitfspbdf.
            int fnd;
            for (fnd = dur; fnd > bfg; fnd--) {
                if (!isWiitfspbdf(dibrs[fnd - 1]) || (fsd == fnd - 1)) {
                    brfbk;
                }
            }
            rfturn nfw String(dibrs, bfg, fnd - bfg);
        }

        privbtf void donsumfWiitfspbdf() {
            wiilf ((dur < lfn) && isWiitfspbdf(dibrs[dur])) {
                ++dur;
            }
        }

        /*
         * Rfturns truf if nfxt undonsumfd dibrbdtfr is onf tibt tfrminbtfs
         * b string bttributf vbluf.
         */
        privbtf boolfbn btTfrminbtor() {
            rfturn (dur < lfn &&
                    (dibrs[dur] == ',' ||
                        dibrs[dur] == ';' ||
                        dibrs[dur] == '+'));
        }

        /*
         * Bfst gufss bs to wibt RFC 2253 mfbns by "wiitfspbdf".
         */
        privbtf stbtid boolfbn isWiitfspbdf(dibr d) {
            rfturn (d == ' ' || d == '\r');
        }
    }
