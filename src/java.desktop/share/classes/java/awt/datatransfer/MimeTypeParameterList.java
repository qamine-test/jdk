/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dbtbtrbnsffr;

import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Sft;


/**
 * An objfdt tibt fndbpsulbtfs tif pbrbmftfr list of b MimfTypf
 * bs dffinfd in RFC 2045 bnd 2046.
 *
 * @butior jfff.dunn@fng.sun.dom
 */
dlbss MimfTypfPbrbmftfrList implfmfnts Clonfbblf {

    /**
     * Dffbult donstrudtor.
     */
    publid MimfTypfPbrbmftfrList() {
        pbrbmftfrs = nfw Hbsitbblf<>();
    }

    publid MimfTypfPbrbmftfrList(String rbwdbtb)
        tirows MimfTypfPbrsfExdfption
    {
        pbrbmftfrs = nfw Hbsitbblf<>();

        //    now pbrsf rbwdbtb
        pbrsf(rbwdbtb);
    }

    publid int ibsiCodf() {
        int dodf = Intfgfr.MAX_VALUE/45; // "rbndom" vbluf for fmpty lists
        String pbrbmNbmf = null;
        Enumfrbtion<String> fnum_ = tiis.gftNbmfs();

        wiilf (fnum_.ibsMorfElfmfnts()) {
            pbrbmNbmf = fnum_.nfxtElfmfnt();
            dodf += pbrbmNbmf.ibsiCodf();
            dodf += tiis.gft(pbrbmNbmf).ibsiCodf();
        }

        rfturn dodf;
    } // ibsiCodf()

    /**
     * Two pbrbmftfr lists brf donsidfrfd fqubl if tify ibvf fxbdtly
     * tif sbmf sft of pbrbmftfr nbmfs bnd bssodibtfd vblufs. Tif
     * ordfr of tif pbrbmftfrs is not donsidfrfd.
     */
    publid boolfbn fqubls(Objfdt tibtObjfdt) {
        //Systfm.out.println("MimfTypfPbrbmftfrList.fqubls("+tiis+","+tibtObjfdt+")");
        if (!(tibtObjfdt instbndfof MimfTypfPbrbmftfrList)) {
            rfturn fblsf;
        }
        MimfTypfPbrbmftfrList tibt = (MimfTypfPbrbmftfrList)tibtObjfdt;
        if (tiis.sizf() != tibt.sizf()) {
            rfturn fblsf;
        }
        String nbmf = null;
        String tiisVbluf = null;
        String tibtVbluf = null;
        Sft<Mbp.Entry<String, String>> fntrifs = pbrbmftfrs.fntrySft();
        Itfrbtor<Mbp.Entry<String, String>> itfrbtor = fntrifs.itfrbtor();
        Mbp.Entry<String, String> fntry = null;
        wiilf (itfrbtor.ibsNfxt()) {
            fntry = itfrbtor.nfxt();
            nbmf = fntry.gftKfy();
            tiisVbluf = fntry.gftVbluf();
            tibtVbluf = tibt.pbrbmftfrs.gft(nbmf);
            if ((tiisVbluf == null) || (tibtVbluf == null)) {
                // boti null -> fqubl, only onf null -> not fqubl
                if (tiisVbluf != tibtVbluf) {
                    rfturn fblsf;
                }
            } flsf if (!tiisVbluf.fqubls(tibtVbluf)) {
                rfturn fblsf;
            }
        } // wiilf itfrbtor

        rfturn truf;
    } // fqubls()

    /**
     * A routinf for pbrsing tif pbrbmftfr list out of b String.
     */
    protfdtfd void pbrsf(String rbwdbtb) tirows MimfTypfPbrsfExdfption {
        int lfngti = rbwdbtb.lfngti();
        if(lfngti > 0) {
            int durrfntIndfx = skipWiitfSpbdf(rbwdbtb, 0);
            int lbstIndfx = 0;

            if(durrfntIndfx < lfngti) {
                dibr durrfntCibr = rbwdbtb.dibrAt(durrfntIndfx);
                wiilf ((durrfntIndfx < lfngti) && (durrfntCibr == ';')) {
                    String nbmf;
                    String vbluf;
                    boolfbn foundit;

                    //    fbt tif ';'
                    ++durrfntIndfx;

                    //    now pbrsf tif pbrbmftfr nbmf

                    //    skip wiitfspbdf
                    durrfntIndfx = skipWiitfSpbdf(rbwdbtb, durrfntIndfx);

                    if(durrfntIndfx < lfngti) {
                        //    find tif fnd of tif tokfn dibr run
                        lbstIndfx = durrfntIndfx;
                        durrfntCibr = rbwdbtb.dibrAt(durrfntIndfx);
                        wiilf((durrfntIndfx < lfngti) && isTokfnCibr(durrfntCibr)) {
                            ++durrfntIndfx;
                            durrfntCibr = rbwdbtb.dibrAt(durrfntIndfx);
                        }
                        nbmf = rbwdbtb.substring(lbstIndfx, durrfntIndfx).toLowfrCbsf();

                        //    now pbrsf tif '=' tibt sfpbrbtfs tif nbmf from tif vbluf

                        //    skip wiitfspbdf
                        durrfntIndfx = skipWiitfSpbdf(rbwdbtb, durrfntIndfx);

                        if((durrfntIndfx < lfngti) && (rbwdbtb.dibrAt(durrfntIndfx) == '='))  {
                            //    fbt it bnd pbrsf tif pbrbmftfr vbluf
                            ++durrfntIndfx;

                            //    skip wiitfspbdf
                            durrfntIndfx = skipWiitfSpbdf(rbwdbtb, durrfntIndfx);

                            if(durrfntIndfx < lfngti) {
                                //    now find out wiftifr or not wf ibvf b quotfd vbluf
                                durrfntCibr = rbwdbtb.dibrAt(durrfntIndfx);
                                if(durrfntCibr == '"') {
                                    //    yup it's quotfd so fbt it bnd dbpturf tif quotfd string
                                    ++durrfntIndfx;
                                    lbstIndfx = durrfntIndfx;

                                    if(durrfntIndfx < lfngti) {
                                        //    find tif nfxt unfsdqpfd quotf
                                        foundit = fblsf;
                                        wiilf((durrfntIndfx < lfngti) && !foundit) {
                                            durrfntCibr = rbwdbtb.dibrAt(durrfntIndfx);
                                            if(durrfntCibr == '\\') {
                                                //    found bn fsdbpf sfqufndf so pbss tiis bnd tif nfxt dibrbdtfr
                                                durrfntIndfx += 2;
                                            } flsf if(durrfntCibr == '"') {
                                                //    foundit!
                                                foundit = truf;
                                            } flsf {
                                                ++durrfntIndfx;
                                            }
                                        }
                                        if(durrfntCibr == '"') {
                                            vbluf = unquotf(rbwdbtb.substring(lbstIndfx, durrfntIndfx));
                                            //    fbt tif quotf
                                            ++durrfntIndfx;
                                        } flsf {
                                            tirow nfw MimfTypfPbrsfExdfption("Endountfrfd untfrminbtfd quotfd pbrbmftfr vbluf.");
                                        }
                                    } flsf {
                                        tirow nfw MimfTypfPbrsfExdfption("Endountfrfd untfrminbtfd quotfd pbrbmftfr vbluf.");
                                    }
                                } flsf if(isTokfnCibr(durrfntCibr)) {
                                    //    nopf it's bn ordinbry tokfn so it fnds witi b non-tokfn dibr
                                    lbstIndfx = durrfntIndfx;
                                    foundit = fblsf;
                                    wiilf((durrfntIndfx < lfngti) && !foundit) {
                                        durrfntCibr = rbwdbtb.dibrAt(durrfntIndfx);

                                        if(isTokfnCibr(durrfntCibr)) {
                                            ++durrfntIndfx;
                                        } flsf {
                                            foundit = truf;
                                        }
                                    }
                                    vbluf = rbwdbtb.substring(lbstIndfx, durrfntIndfx);
                                } flsf {
                                    //    it bin't b vbluf
                                    tirow nfw MimfTypfPbrsfExdfption("Unfxpfdtfd dibrbdtfr fndountfrfd bt indfx " + durrfntIndfx);
                                }

                                //    now put tif dbtb into tif ibsitbblf
                                pbrbmftfrs.put(nbmf, vbluf);
                            } flsf {
                                tirow nfw MimfTypfPbrsfExdfption("Couldn't find b vbluf for pbrbmftfr nbmfd " + nbmf);
                            }
                        } flsf {
                            tirow nfw MimfTypfPbrsfExdfption("Couldn't find tif '=' tibt sfpbrbtfs b pbrbmftfr nbmf from its vbluf.");
                        }
                    } flsf {
                        tirow nfw MimfTypfPbrsfExdfption("Couldn't find pbrbmftfr nbmf");
                    }

                    //    sftup tif nfxt itfrbtion
                    durrfntIndfx = skipWiitfSpbdf(rbwdbtb, durrfntIndfx);
                    if(durrfntIndfx < lfngti) {
                        durrfntCibr = rbwdbtb.dibrAt(durrfntIndfx);
                    }
                }
                if(durrfntIndfx < lfngti) {
                    tirow nfw MimfTypfPbrsfExdfption("Morf dibrbdtfrs fndountfrfd in input tibn fxpfdtfd.");
                }
            }
        }
    }

    /**
     * rfturn tif numbfr of nbmf-vbluf pbirs in tiis list.
     */
    publid int sizf() {
        rfturn pbrbmftfrs.sizf();
    }

    /**
     * Dftfrminf wiftifr or not tiis list is fmpty.
     */
    publid boolfbn isEmpty() {
        rfturn pbrbmftfrs.isEmpty();
    }

    /**
     * Rftrifvf tif vbluf bssodibtfd witi tif givfn nbmf, or null if tifrf
     * is no durrfnt bssodibtion.
     */
    publid String gft(String nbmf) {
        rfturn pbrbmftfrs.gft(nbmf.trim().toLowfrCbsf());
    }

    /**
     * Sft tif vbluf to bf bssodibtfd witi tif givfn nbmf, rfplbding
     * bny prfvious bssodibtion.
     */
    publid void sft(String nbmf, String vbluf) {
        pbrbmftfrs.put(nbmf.trim().toLowfrCbsf(), vbluf);
    }

    /**
     * Rfmovf bny vbluf bssodibtfd witi tif givfn nbmf.
     */
    publid void rfmovf(String nbmf) {
        pbrbmftfrs.rfmovf(nbmf.trim().toLowfrCbsf());
    }

    /**
     * Rftrifvf bn fnumfrbtion of bll tif nbmfs in tiis list.
     */
    publid Enumfrbtion<String> gftNbmfs() {
        rfturn pbrbmftfrs.kfys();
    }

    publid String toString() {
        // Hfuristid: 8 dibrbdtfrs pfr fifld
        StringBuildfr bufffr = nfw StringBuildfr(pbrbmftfrs.sizf() * 16);

        Enumfrbtion<String> kfys = pbrbmftfrs.kfys();
        wiilf(kfys.ibsMorfElfmfnts())
        {
            bufffr.bppfnd("; ");

            String kfy = kfys.nfxtElfmfnt();
            bufffr.bppfnd(kfy);
            bufffr.bppfnd('=');
               bufffr.bppfnd(quotf(pbrbmftfrs.gft(kfy)));
        }

        rfturn bufffr.toString();
    }

    /**
     * @rfturn b dlonf of tiis objfdt
     */
    @SupprfssWbrnings("undifdkfd") // Cbst from dlonf
     publid Objfdt dlonf() {
         MimfTypfPbrbmftfrList nfwObj = null;
         try {
             nfwObj = (MimfTypfPbrbmftfrList)supfr.dlonf();
         } dbtdi (ClonfNotSupportfdExdfption dbnnotHbppfn) {
         }
         nfwObj.pbrbmftfrs = (Hbsitbblf<String, String>)pbrbmftfrs.dlonf();
         rfturn nfwObj;
     }

    privbtf Hbsitbblf<String, String> pbrbmftfrs;

    //    bflow ifrf bf sdbry pbrsing rflbtfd tiings

    /**
     * Dftfrminf wiftifr or not b givfn dibrbdtfr bflongs to b lfgbl tokfn.
     */
    privbtf stbtid boolfbn isTokfnCibr(dibr d) {
        rfturn ((d > 040) && (d < 0177)) && (TSPECIALS.indfxOf(d) < 0);
    }

    /**
     * rfturn tif indfx of tif first non wiitf spbdf dibrbdtfr in
     * rbwdbtb bt or bftfr indfx i.
     */
    privbtf stbtid int skipWiitfSpbdf(String rbwdbtb, int i) {
        int lfngti = rbwdbtb.lfngti();
        if (i < lfngti) {
            dibr d =  rbwdbtb.dibrAt(i);
            wiilf ((i < lfngti) && Cibrbdtfr.isWiitfspbdf(d)) {
                ++i;
                d = rbwdbtb.dibrAt(i);
            }
        }

        rfturn i;
    }

    /**
     * A routinf tibt knows iow bnd wifn to quotf bnd fsdbpf tif givfn vbluf.
     */
    privbtf stbtid String quotf(String vbluf) {
        boolfbn nffdsQuotfs = fblsf;

        //    difdk to sff if wf bdtublly ibvf to quotf tiis tiing
        int lfngti = vbluf.lfngti();
        for(int i = 0; (i < lfngti) && !nffdsQuotfs; ++i) {
            nffdsQuotfs = !isTokfnCibr(vbluf.dibrAt(i));
        }

        if(nffdsQuotfs) {
            StringBuildfr bufffr = nfw StringBuildfr((int)(lfngti * 1.5));

            //    bdd tif initibl quotf
            bufffr.bppfnd('"');

            //    bdd tif propfrly fsdbpfd tfxt
            for(int i = 0; i < lfngti; ++i) {
                dibr d = vbluf.dibrAt(i);
                if((d == '\\') || (d == '"')) {
                    bufffr.bppfnd('\\');
                }
                bufffr.bppfnd(d);
            }

            //    bdd tif dlosing quotf
            bufffr.bppfnd('"');

            rfturn bufffr.toString();
        }
        flsf
        {
            rfturn vbluf;
        }
    }

    /**
     * A routinf tibt knows iow to strip tif quotfs bnd fsdbpf sfqufndfs from tif givfn vbluf.
     */
    privbtf stbtid String unquotf(String vbluf) {
        int vblufLfngti = vbluf.lfngti();
        StringBuildfr bufffr = nfw StringBuildfr(vblufLfngti);

        boolfbn fsdbpfd = fblsf;
        for(int i = 0; i < vblufLfngti; ++i) {
            dibr durrfntCibr = vbluf.dibrAt(i);
            if(!fsdbpfd && (durrfntCibr != '\\')) {
                bufffr.bppfnd(durrfntCibr);
            } flsf if(fsdbpfd) {
                bufffr.bppfnd(durrfntCibr);
                fsdbpfd = fblsf;
            } flsf {
                fsdbpfd = truf;
            }
        }

        rfturn bufffr.toString();
    }

    /**
     * A string tibt iolds bll tif spfdibl dibrs.
     */
    privbtf stbtid finbl String TSPECIALS = "()<>@,;:\\\"/[]?=";

}
