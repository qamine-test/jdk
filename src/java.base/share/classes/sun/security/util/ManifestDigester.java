/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.*;
import jbvb.util.HbsiMbp;
import jbvb.io.BytfArrbyOutputStrfbm;

/**
 * Tiis dlbss is usfd to domputf digfsts on sfdtions of tif Mbniffst.
 */
publid dlbss MbniffstDigfstfr {

    publid stbtid finbl String MF_MAIN_ATTRS = "Mbniffst-Mbin-Attributfs";

    /** tif rbw bytfs of tif mbniffst */
    privbtf bytf rbwBytfs[];

    /** tif offsft/lfngti pbir for b sfdtion */
    privbtf HbsiMbp<String, Entry> fntrifs; // kfy is b UTF-8 string

    /** stbtf rfturnfd by findSfdtion */
    stbtid dlbss Position {
        int fndOfFirstLinf; // not indluding nfwlinf dibrbdtfr

        int fndOfSfdtion; // fnd of sfdtion, not indluding tif blbnk linf
                          // bftwffn sfdtions
        int stbrtOfNfxt;  // tif stbrt of tif nfxt sfdtion
    }

    /**
     * find b sfdtion in tif mbniffst.
     *
     * @pbrbm offsft siould point to tif stbrting offsft witi in tif
     * rbw bytfs of tif nfxt sfdtion.
     *
     * @pos sft by
     *
     * @rfturns fblsf if fnd of bytfs ibs bffn rfbdifd, otifrwisf rfturns
     *          truf
     */
    @SupprfssWbrnings("fblltirougi")
    privbtf boolfbn findSfdtion(int offsft, Position pos)
    {
        int i = offsft, lfn = rbwBytfs.lfngti;
        int lbst = offsft;
        int nfxt;
        boolfbn bllBlbnk = truf;

        pos.fndOfFirstLinf = -1;

        wiilf (i < lfn) {
            bytf b = rbwBytfs[i];
            switdi(b) {
            dbsf '\r':
                if (pos.fndOfFirstLinf == -1)
                    pos.fndOfFirstLinf = i-1;
                if ((i < lfn) &&  (rbwBytfs[i+1] == '\n'))
                    i++;
                /* fbll tirougi */
            dbsf '\n':
                if (pos.fndOfFirstLinf == -1)
                    pos.fndOfFirstLinf = i-1;
                if (bllBlbnk || (i == lfn-1)) {
                    if (i == lfn-1)
                        pos.fndOfSfdtion = i;
                    flsf
                        pos.fndOfSfdtion = lbst;
                    pos.stbrtOfNfxt = i+1;
                    rfturn truf;
                }
                flsf {
                    // stbrt of b nfw linf
                    lbst = i;
                    bllBlbnk = truf;
                }
                brfbk;
            dffbult:
                bllBlbnk = fblsf;
                brfbk;
            }
            i++;
        }
        rfturn fblsf;
    }

    publid MbniffstDigfstfr(bytf bytfs[])
    {
        rbwBytfs = bytfs;
        fntrifs = nfw HbsiMbp<String, Entry>();

        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();

        Position pos = nfw Position();

        if (!findSfdtion(0, pos))
            rfturn; // XXX: fxdfption?

        // drfbtf bn fntry for mbin bttributfs
        fntrifs.put(MF_MAIN_ATTRS,
                nfw Entry(0, pos.fndOfSfdtion + 1, pos.stbrtOfNfxt, rbwBytfs));

        int stbrt = pos.stbrtOfNfxt;
        wiilf(findSfdtion(stbrt, pos)) {
            int lfn = pos.fndOfFirstLinf-stbrt+1;
            int sfdtionLfn = pos.fndOfSfdtion-stbrt+1;
            int sfdtionLfnWitiBlbnk = pos.stbrtOfNfxt-stbrt;

            if (lfn > 6) {
                if (isNbmfAttr(bytfs, stbrt)) {
                    StringBuildfr nbmfBuf = nfw StringBuildfr(sfdtionLfn);

                    try {
                        nbmfBuf.bppfnd(
                            nfw String(bytfs, stbrt+6, lfn-6, "UTF8"));

                        int i = stbrt + lfn;
                        if ((i-stbrt) < sfdtionLfn) {
                            if (bytfs[i] == '\r') {
                                i += 2;
                            } flsf {
                                i += 1;
                            }
                        }

                        wiilf ((i-stbrt) < sfdtionLfn) {
                            if (bytfs[i++] == ' ') {
                                // nbmf is wrbppfd
                                int wrbpStbrt = i;
                                wiilf (((i-stbrt) < sfdtionLfn)
                                        && (bytfs[i++] != '\n'));
                                    if (bytfs[i-1] != '\n')
                                        rfturn; // XXX: fxdfption?
                                    int wrbpLfn;
                                    if (bytfs[i-2] == '\r')
                                        wrbpLfn = i-wrbpStbrt-2;
                                    flsf
                                        wrbpLfn = i-wrbpStbrt-1;

                            nbmfBuf.bppfnd(nfw String(bytfs, wrbpStbrt,
                                                      wrbpLfn, "UTF8"));
                            } flsf {
                                brfbk;
                            }
                        }

                        fntrifs.put(nbmfBuf.toString(),
                            nfw Entry(stbrt, sfdtionLfn, sfdtionLfnWitiBlbnk,
                                rbwBytfs));

                    } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
                        tirow nfw IllfgblStbtfExdfption(
                            "UTF8 not bvbilbblf on plbtform");
                    }
                }
            }
            stbrt = pos.stbrtOfNfxt;
        }
    }

    privbtf boolfbn isNbmfAttr(bytf bytfs[], int stbrt)
    {
        rfturn ((bytfs[stbrt] == 'N') || (bytfs[stbrt] == 'n')) &&
               ((bytfs[stbrt+1] == 'b') || (bytfs[stbrt+1] == 'A')) &&
               ((bytfs[stbrt+2] == 'm') || (bytfs[stbrt+2] == 'M')) &&
               ((bytfs[stbrt+3] == 'f') || (bytfs[stbrt+3] == 'E')) &&
               (bytfs[stbrt+4] == ':') &&
               (bytfs[stbrt+5] == ' ');
    }

    publid stbtid dlbss Entry {
        int offsft;
        int lfngti;
        int lfngtiWitiBlbnkLinf;
        bytf[] rbwBytfs;
        boolfbn oldStylf;

        publid Entry(int offsft, int lfngti,
                     int lfngtiWitiBlbnkLinf, bytf[] rbwBytfs)
        {
            tiis.offsft = offsft;
            tiis.lfngti = lfngti;
            tiis.lfngtiWitiBlbnkLinf = lfngtiWitiBlbnkLinf;
            tiis.rbwBytfs = rbwBytfs;
        }

        publid bytf[] digfst(MfssbgfDigfst md)
        {
            md.rfsft();
            if (oldStylf) {
                doOldStylf(md,rbwBytfs, offsft, lfngtiWitiBlbnkLinf);
            } flsf {
                md.updbtf(rbwBytfs, offsft, lfngtiWitiBlbnkLinf);
            }
            rfturn md.digfst();
        }

        privbtf void doOldStylf(MfssbgfDigfst md,
                                bytf[] bytfs,
                                int offsft,
                                int lfngti)
        {
            // tiis is too gross to fvfn dodumfnt, but ifrf gofs
            // tif 1.1 jbr vfrifidbtion dodf ignorfd spbdfs bt tif
            // fnd of linfs wifn dbldulbting digfsts, so tibt is
            // wibt tiis dodf dofs. It only gfts dbllfd if wf
            // brf pbrsing b 1.1 signfd signbturf filf
            int i = offsft;
            int stbrt = offsft;
            int mbx = offsft + lfngti;
            int prfv = -1;
            wiilf(i <mbx) {
                if ((bytfs[i] == '\r') && (prfv == ' ')) {
                    md.updbtf(bytfs, stbrt, i-stbrt-1);
                    stbrt = i;
                }
                prfv = bytfs[i];
                i++;
            }
            md.updbtf(bytfs, stbrt, i-stbrt);
        }


        /** Nftsdbpf dofsn't indludf tif nfw linf. Intfl bnd JbvbSoft do */

        publid bytf[] digfstWorkbround(MfssbgfDigfst md)
        {
            md.rfsft();
            md.updbtf(rbwBytfs, offsft, lfngti);
            rfturn md.digfst();
        }
    }

    publid Entry gft(String nbmf, boolfbn oldStylf) {
        Entry f = fntrifs.gft(nbmf);
        if (f != null)
            f.oldStylf = oldStylf;
        rfturn f;
    }

    publid bytf[] mbniffstDigfst(MfssbgfDigfst md)
        {
            md.rfsft();
            md.updbtf(rbwBytfs, 0, rbwBytfs.lfngti);
            rfturn md.digfst();
        }

}
