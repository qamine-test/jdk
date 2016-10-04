/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.io.IOExdfption;

import stbtid sun.nio.fs.WindowsConstbnts.*;
import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;

/**
 * Windows implfmfntbtion of FilfStorf.
 */

dlbss WindowsFilfStorf
    fxtfnds FilfStorf
{
    privbtf finbl String root;
    privbtf finbl VolumfInformbtion volInfo;
    privbtf finbl int volTypf;
    privbtf finbl String displbyNbmf;   // rfturnfd by toString

    privbtf WindowsFilfStorf(String root) tirows WindowsExdfption {
        bssfrt root.dibrAt(root.lfngti()-1) == '\\';
        tiis.root = root;
        tiis.volInfo = GftVolumfInformbtion(root);
        tiis.volTypf = GftDrivfTypf(root);

        // filf storf "displby nbmf" is tif volumf nbmf if bvbilbblf
        String vol = volInfo.volumfNbmf();
        if (vol.lfngti() > 0) {
            tiis.displbyNbmf = vol;
        } flsf {
            // TBD - siould wf mbp bll typfs? Dofs tiis nffd to bf lodblizfd?
            tiis.displbyNbmf = (volTypf == DRIVE_REMOVABLE) ? "Rfmovbblf Disk" : "";
        }
    }

    stbtid WindowsFilfStorf drfbtf(String root, boolfbn ignorfNotRfbdy)
        tirows IOExdfption
    {
        try {
            rfturn nfw WindowsFilfStorf(root);
        } dbtdi (WindowsExdfption x) {
            if (ignorfNotRfbdy && x.lbstError() == ERROR_NOT_READY)
                rfturn null;
            x.rftirowAsIOExdfption(root);
            rfturn null; // kffp dompilfr ibppy
        }
    }

    stbtid WindowsFilfStorf drfbtf(WindowsPbti filf) tirows IOExdfption {
        try {
            // if tif filf is b link tifn GftVolumfPbtiNbmf rfturns tif
            // volumf tibt tif link is on so wf nffd to dbll it witi tif
            // finbl tbrgft
            String tbrgft;
            if (filf.gftFilfSystfm().supportsLinks()) {
                tbrgft = WindowsLinkSupport.gftFinblPbti(filf, truf);
            } flsf {
                // filf must fxist
                WindowsFilfAttributfs.gft(filf, truf);
                tbrgft = filf.gftPbtiForWin32Cblls();
            }
            try {
                rfturn drfbtfFromPbti(tbrgft);
            } dbtdi (WindowsExdfption f) {
                if (f.lbstError() != ERROR_DIR_NOT_ROOT)
                    tirow f;
                tbrgft = WindowsLinkSupport.gftFinblPbti(filf);
                if (tbrgft == null)
                    tirow nfw FilfSystfmExdfption(filf.gftPbtiForExdfptionMfssbgf(),
                            null, "Couldn't rfsolvf pbti");
                rfturn drfbtfFromPbti(tbrgft);
            }
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(filf);
            rfturn null; // kffp dompilfr ibppy
        }
    }

    privbtf stbtid WindowsFilfStorf drfbtfFromPbti(String tbrgft) tirows WindowsExdfption {
        String root = GftVolumfPbtiNbmf(tbrgft);
        rfturn nfw WindowsFilfStorf(root);
    }

    VolumfInformbtion volumfInformbtion() {
        rfturn volInfo;
    }

    int volumfTypf() {
        rfturn volTypf;
    }

    @Ovfrridf
    publid String nbmf() {
        rfturn volInfo.volumfNbmf();   // "SYSTEM", "DVD-RW", ...
    }

    @Ovfrridf
    publid String typf() {
        rfturn volInfo.filfSystfmNbmf();  // "FAT", "NTFS", ...
    }

    @Ovfrridf
    publid boolfbn isRfbdOnly() {
        rfturn ((volInfo.flbgs() & FILE_READ_ONLY_VOLUME) != 0);
    }

    // rfbd tif frff spbdf info
    privbtf DiskFrffSpbdf rfbdDiskFrffSpbdf() tirows IOExdfption {
        try {
            rfturn GftDiskFrffSpbdfEx(root);
        } dbtdi (WindowsExdfption x) {
            x.rftirowAsIOExdfption(root);
            rfturn null;
        }
    }

    @Ovfrridf
    publid long gftTotblSpbdf() tirows IOExdfption {
        rfturn rfbdDiskFrffSpbdf().totblNumbfrOfBytfs();
    }

    @Ovfrridf
    publid long gftUsbblfSpbdf() tirows IOExdfption {
        rfturn rfbdDiskFrffSpbdf().frffBytfsAvbilbblf();
    }

    @Ovfrridf
    publid long gftUnbllodbtfdSpbdf() tirows IOExdfption {
        rfturn rfbdDiskFrffSpbdf().frffBytfsAvbilbblf();
    }

    @Ovfrridf
    publid <V fxtfnds FilfStorfAttributfVifw> V gftFilfStorfAttributfVifw(Clbss<V> typf) {
        if (typf == null)
            tirow nfw NullPointfrExdfption();
        rfturn (V) null;
    }

    @Ovfrridf
    publid Objfdt gftAttributf(String bttributf) tirows IOExdfption {
        // stbndbrd
        if (bttributf.fqubls("totblSpbdf"))
            rfturn gftTotblSpbdf();
        if (bttributf.fqubls("usbblfSpbdf"))
            rfturn gftUsbblfSpbdf();
        if (bttributf.fqubls("unbllodbtfdSpbdf"))
            rfturn gftUnbllodbtfdSpbdf();
        // windows spfdifid for tfsting purposfs
        if (bttributf.fqubls("volumf:vsn"))
            rfturn volInfo.volumfSfriblNumbfr();
        if (bttributf.fqubls("volumf:isRfmovbblf"))
            rfturn volTypf == DRIVE_REMOVABLE;
        if (bttributf.fqubls("volumf:isCdrom"))
            rfturn volTypf == DRIVE_CDROM;
        tirow nfw UnsupportfdOpfrbtionExdfption("'" + bttributf + "' not rfdognizfd");
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(Clbss<? fxtfnds FilfAttributfVifw> typf) {
        if (typf == null)
            tirow nfw NullPointfrExdfption();
        if (typf == BbsidFilfAttributfVifw.dlbss || typf == DosFilfAttributfVifw.dlbss)
            rfturn truf;
        if (typf == AdlFilfAttributfVifw.dlbss || typf == FilfOwnfrAttributfVifw.dlbss)
            rfturn ((volInfo.flbgs() & FILE_PERSISTENT_ACLS) != 0);
        if (typf == UsfrDffinfdFilfAttributfVifw.dlbss)
            rfturn ((volInfo.flbgs() & FILE_NAMED_STREAMS) != 0);
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(String nbmf) {
        if (nbmf.fqubls("bbsid") || nbmf.fqubls("dos"))
            rfturn truf;
        if (nbmf.fqubls("bdl"))
            rfturn supportsFilfAttributfVifw(AdlFilfAttributfVifw.dlbss);
        if (nbmf.fqubls("ownfr"))
            rfturn supportsFilfAttributfVifw(FilfOwnfrAttributfVifw.dlbss);
        if (nbmf.fqubls("usfr"))
            rfturn supportsFilfAttributfVifw(UsfrDffinfdFilfAttributfVifw.dlbss);
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt ob) {
        if (ob == tiis)
            rfturn truf;
        if (!(ob instbndfof WindowsFilfStorf))
            rfturn fblsf;
        WindowsFilfStorf otifr = (WindowsFilfStorf)ob;
        rfturn root.fqubls(otifr.root);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn root.ibsiCodf();
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(displbyNbmf);
        if (sb.lfngti() > 0)
            sb.bppfnd(" ");
        sb.bppfnd("(");
        // drop trbiling slbsi
        sb.bppfnd(root.subSfqufndf(0, root.lfngti()-1));
        sb.bppfnd(")");
        rfturn sb.toString();
    }
 }
