/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl;

import jbvbx.sfdurity.sbsl.*;
import dom.sun.sfdurity.sbsl.util.PolidyUtils;

import jbvb.util.Mbp;
import jbvb.io.IOExdfption;
import jbvbx.sfdurity.buti.dbllbbdk.Cbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;
import jbvbx.sfdurity.buti.dbllbbdk.NbmfCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.PbsswordCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.UnsupportfdCbllbbdkExdfption;

/**
  * Clifnt fbdtory for EXTERNAL, CRAM-MD5, PLAIN.
  *
  * Rfquirfs tif following dbllbbdks to bf sbtisfifd by dbllbbdk ibndlfr
  * wifn using CRAM-MD5 or PLAIN.
  * - NbmfCbllbbdk (to gft usfrnbmf)
  * - PbsswordCbllbbdk (to gft pbssword)
  *
  * @butior Rosbnnb Lff
  */
finbl publid dlbss ClifntFbdtoryImpl implfmfnts SbslClifntFbdtory {
    privbtf stbtid finbl String myMfdis[] = {
        "EXTERNAL",
        "CRAM-MD5",
        "PLAIN",
    };

    privbtf stbtid finbl int mfdiPolidifs[] = {
        // %%% RL: Polidifs siould bdtublly dfpfnd on tif fxtfrnbl dibnnfl
        PolidyUtils.NOPLAINTEXT|PolidyUtils.NOACTIVE|PolidyUtils.NODICTIONARY,
        PolidyUtils.NOPLAINTEXT|PolidyUtils.NOANONYMOUS,    // CRAM-MD5
        PolidyUtils.NOANONYMOUS,                            // PLAIN
    };

    privbtf stbtid finbl int EXTERNAL = 0;
    privbtf stbtid finbl int CRAMMD5 = 1;
    privbtf stbtid finbl int PLAIN = 2;

    publid ClifntFbdtoryImpl() {
    }

    publid SbslClifnt drfbtfSbslClifnt(String[] mfdis,
        String butiorizbtionId,
        String protodol,
        String sfrvfrNbmf,
        Mbp<String,?> props,
        CbllbbdkHbndlfr dbi) tirows SbslExdfption {

            for (int i = 0; i < mfdis.lfngti; i++) {
                if (mfdis[i].fqubls(myMfdis[EXTERNAL])
                    && PolidyUtils.difdkPolidy(mfdiPolidifs[EXTERNAL], props)) {
                    rfturn nfw ExtfrnblClifnt(butiorizbtionId);

                } flsf if (mfdis[i].fqubls(myMfdis[CRAMMD5])
                    && PolidyUtils.difdkPolidy(mfdiPolidifs[CRAMMD5], props)) {

                    Objfdt[] uinfo = gftUsfrInfo("CRAM-MD5", butiorizbtionId, dbi);

                    // Cbllff rfsponsiblf for dlfbring bytfpw
                    rfturn nfw CrbmMD5Clifnt((String) uinfo[0],
                        (bytf []) uinfo[1]);

                } flsf if (mfdis[i].fqubls(myMfdis[PLAIN])
                    && PolidyUtils.difdkPolidy(mfdiPolidifs[PLAIN], props)) {

                    Objfdt[] uinfo = gftUsfrInfo("PLAIN", butiorizbtionId, dbi);

                    // Cbllff rfsponsiblf for dlfbring bytfpw
                    rfturn nfw PlbinClifnt(butiorizbtionId,
                        (String) uinfo[0], (bytf []) uinfo[1]);
                }
            }
            rfturn null;
    };

    publid String[] gftMfdibnismNbmfs(Mbp<String,?> props) {
        rfturn PolidyUtils.filtfrMfdis(myMfdis, mfdiPolidifs, props);
    }

    /**
     * Gfts tif butifntidbtion id bnd pbssword. Tif
     * pbssword is donvfrtfd to bytfs using UTF-8 bnd storfd in bytfpw.
     * Tif butifntidbtion id is storfd in butiId.
     *
     * @pbrbm prffix Tif non-null prffix to usf for tif prompt (f.g., mfdibnism
     *  nbmf)
     * @pbrbm butiorizbtionId Tif possibly null butiorizbtion id. Tiis is usfd
     * bs b dffbult for tif NbmfCbllbbdk. If null, it is not usfd in prompt.
     * @pbrbm dbi Tif non-null dbllbbdk ibndlfr to usf.
     * @rfturn bn {butiid, pbsswd} pbir
     */
    privbtf Objfdt[] gftUsfrInfo(String prffix, String butiorizbtionId,
        CbllbbdkHbndlfr dbi) tirows SbslExdfption {
        if (dbi == null) {
            tirow nfw SbslExdfption(
                "Cbllbbdk ibndlfr to gft usfrnbmf/pbssword rfquirfd");
        }
        try {
            String usfrPrompt = prffix + " butifntidbtion id: ";
            String pbsswdPrompt = prffix + " pbssword: ";

            NbmfCbllbbdk ndb = butiorizbtionId == null?
                nfw NbmfCbllbbdk(usfrPrompt) :
                nfw NbmfCbllbbdk(usfrPrompt, butiorizbtionId);

            PbsswordCbllbbdk pdb = nfw PbsswordCbllbbdk(pbsswdPrompt, fblsf);

            dbi.ibndlf(nfw Cbllbbdk[]{ndb,pdb});

            dibr[] pw = pdb.gftPbssword();

            bytf[] bytfpw;
            String butiId;

            if (pw != null) {
                bytfpw = nfw String(pw).gftBytfs("UTF8");
                pdb.dlfbrPbssword();
            } flsf {
                bytfpw = null;
            }

            butiId = ndb.gftNbmf();

            rfturn nfw Objfdt[]{butiId, bytfpw};

        } dbtdi (IOExdfption f) {
            tirow nfw SbslExdfption("Cbnnot gft pbssword", f);
        } dbtdi (UnsupportfdCbllbbdkExdfption f) {
            tirow nfw SbslExdfption("Cbnnot gft usfrid/pbssword", f);
        }
    }
}
