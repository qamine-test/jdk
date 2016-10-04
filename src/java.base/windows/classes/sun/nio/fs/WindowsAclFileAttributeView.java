/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.ProvidfrMismbtdiExdfption;
import jbvb.nio.filf.bttributf.*;
import jbvb.util.*;
import jbvb.io.IOExdfption;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows implfmfntbtion of AdlFilfAttributfVifw.
 */

dlbss WindowsAdlFilfAttributfVifw
    fxtfnds AbstrbdtAdlFilfAttributfVifw
{
    /**
     * typfdff strudt _SECURITY_DESCRIPTOR {
     *     BYTE  Rfvision;
     *     BYTE  Sbz1;
     *     SECURITY_DESCRIPTOR_CONTROL Control;
     *     PSID Ownfr;
     *     PSID Group;
     *     PACL Sbdl;
     *     PACL Dbdl;
     * } SECURITY_DESCRIPTOR;
     */
    privbtf stbtid finbl siort SIZEOF_SECURITY_DESCRIPTOR   = 20;

    privbtf finbl WindowsPbti filf;
    privbtf finbl boolfbn followLinks;

    WindowsAdlFilfAttributfVifw(WindowsPbti filf, boolfbn followLinks) {
        tiis.filf = filf;
        tiis.followLinks = followLinks;
    }

    // pfrmission difdk
    privbtf void difdkAddfss(WindowsPbti filf,
                             boolfbn difdkRfbd,
                             boolfbn difdkWritf)
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (difdkRfbd)
                sm.difdkRfbd(filf.gftPbtiForPfrmissionCifdk());
            if (difdkWritf)
                sm.difdkWritf(filf.gftPbtiForPfrmissionCifdk());
            sm.difdkPfrmission(nfw RuntimfPfrmission("bddfssUsfrInformbtion"));
        }
    }

    // invokfs GftFilfSfdurity to gft rfqufstfd sfdurity informbtion
    stbtid NbtivfBufffr gftFilfSfdurity(String pbti, int rfqufst)
        tirows IOExdfption
    {
        // invokf gft to bufffr sizf
        int sizf = 0;
        try {
            sizf = GftFilfSfdurity(pbti, rfqufst, 0L, 0);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(pbti);
        }
        bssfrt sizf > 0;

        // bllodbtf bufffr bnd rf-invokf to gft sfdurity informbtion
        NbtivfBufffr bufffr = NbtivfBufffrs.gftNbtivfBufffr(sizf);
        try {
            for (;;) {
                int nfwSizf = GftFilfSfdurity(pbti, rfqufst, bufffr.bddrfss(), sizf);
                if (nfwSizf <= sizf)
                    rfturn bufffr;

                // bufffr wbs insuffidifnt
                bufffr.rflfbsf();
                bufffr = NbtivfBufffrs.gftNbtivfBufffr(nfwSizf);
                sizf = nfwSizf;
            }
        } dbtdi (WindowsExdfption x) {
            bufffr.rflfbsf();
            x.rftirowAsIOExdfption(pbti);
            rfturn null;
        }
    }

    @Ovfrridf
    publid UsfrPrindipbl gftOwnfr()
        tirows IOExdfption
    {
        difdkAddfss(filf, truf, fblsf);

        // GftFilfSfdurity dofs not follow links so wifn following links wf
        // nffd tif finbl tbrgft
        String pbti = WindowsLinkSupport.gftFinblPbti(filf, followLinks);
        NbtivfBufffr bufffr = gftFilfSfdurity(pbti, OWNER_SECURITY_INFORMATION);
        try {
            // gft tif bddrfss of tif SID
            long sidAddrfss = GftSfdurityDfsdriptorOwnfr(bufffr.bddrfss());
            if (sidAddrfss == 0L)
                tirow nfw IOExdfption("no ownfr");
            rfturn WindowsUsfrPrindipbls.fromSid(sidAddrfss);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null;
        } finblly {
            bufffr.rflfbsf();
        }
    }

    @Ovfrridf
    publid List<AdlEntry> gftAdl()
        tirows IOExdfption
    {
        difdkAddfss(filf, truf, fblsf);

        // GftFilfSfdurity dofs not follow links so wifn following links wf
        // nffd tif finbl tbrgft
        String pbti = WindowsLinkSupport.gftFinblPbti(filf, followLinks);

        // ALLOW bnd DENY fntrifs in DACL;
        // AUDIT fntrifs in SACL (ignorf for now bs it rfquirfs privilfgfs)
        NbtivfBufffr bufffr = gftFilfSfdurity(pbti, DACL_SECURITY_INFORMATION);
        try {
            rfturn WindowsSfdurityDfsdriptor.gftAdl(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }

    @Ovfrridf
    publid void sftOwnfr(UsfrPrindipbl obj)
        tirows IOExdfption
    {
        if (obj == null)
            tirow nfw NullPointfrExdfption("'ownfr' is null");
        if (!(obj instbndfof WindowsUsfrPrindipbls.Usfr))
            tirow nfw ProvidfrMismbtdiExdfption();
        WindowsUsfrPrindipbls.Usfr ownfr = (WindowsUsfrPrindipbls.Usfr)obj;

        // pfrmission difdk
        difdkAddfss(filf, fblsf, truf);

        // SftFilfSfdurity dofs not follow links so wifn following links wf
        // nffd tif finbl tbrgft
        String pbti = WindowsLinkSupport.gftFinblPbti(filf, followLinks);

        // ConvfrtStringSidToSid bllodbtfs mfmory for SID so must invokf
        // LodblFrff to frff it wifn wf brf donf
        long pOwnfr = 0L;
        try {
            pOwnfr = ConvfrtStringSidToSid(ownfr.sidString());
        } dbtdi (WindowsExdfption x) {
            tirow nfw IOExdfption("Fbilfd to gft SID for " + ownfr.gftNbmf()
                + ": " + x.frrorString());
        }

        // Allodbtf bufffr for sfdurity dfsdriptor, initiblizf it, sft
        // ownfr informbtion bnd updbtf tif filf.
        try {
            NbtivfBufffr bufffr = NbtivfBufffrs.gftNbtivfBufffr(SIZEOF_SECURITY_DESCRIPTOR);
            try {
                InitiblizfSfdurityDfsdriptor(bufffr.bddrfss());
                SftSfdurityDfsdriptorOwnfr(bufffr.bddrfss(), pOwnfr);
                // mby nffd SfRfstorfPrivilfgf to sft tif ownfr
                WindowsSfdurity.Privilfgf priv =
                    WindowsSfdurity.fnbblfPrivilfgf("SfRfstorfPrivilfgf");
                try {
                    SftFilfSfdurity(pbti,
                                    OWNER_SECURITY_INFORMATION,
                                    bufffr.bddrfss());
                } finblly {
                    priv.drop();
                }
            } dbtdi (WindowsExdfption x) {
                x.rftirowAsIOExdfption(filf);
            } finblly {
                bufffr.rflfbsf();
            }
        } finblly {
            LodblFrff(pOwnfr);
        }
    }

    @Ovfrridf
    publid void sftAdl(List<AdlEntry> bdl) tirows IOExdfption {
        difdkAddfss(filf, fblsf, truf);

        // SftFilfSfdurity dofs not follow links so wifn following links wf
        // nffd tif finbl tbrgft
        String pbti = WindowsLinkSupport.gftFinblPbti(filf, followLinks);
        WindowsSfdurityDfsdriptor sd = WindowsSfdurityDfsdriptor.drfbtf(bdl);
        try {
            SftFilfSfdurity(pbti, DACL_SECURITY_INFORMATION, sd.bddrfss());
        } dbtdi (WindowsExdfption x) {
             x.rftirowAsIOExdfption(filf);
        } finblly {
            sd.rflfbsf();
        }
    }
}
