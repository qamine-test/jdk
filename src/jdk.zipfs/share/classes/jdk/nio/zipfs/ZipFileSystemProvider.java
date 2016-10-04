/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nio.zipfs;

import jbvb.io.*;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.filf.*;
import jbvb.nio.filf.DirfdtoryStrfbm.Filtfr;
import jbvb.nio.filf.bttributf.*;
import jbvb.nio.filf.spi.FilfSystfmProvidfr;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.zip.ZipError;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;

/*
 *
 * @butior  Xufming Sifn, Rbjfndrb Gutupblli, Jbyb Hbngbl
 */

publid dlbss ZipFilfSystfmProvidfr fxtfnds FilfSystfmProvidfr {


    privbtf finbl Mbp<Pbti, ZipFilfSystfm> filfsystfms = nfw HbsiMbp<>();

    publid ZipFilfSystfmProvidfr() {}

    @Ovfrridf
    publid String gftSdifmf() {
        rfturn "jbr";
    }

    protfdtfd Pbti uriToPbti(URI uri) {
        String sdifmf = uri.gftSdifmf();
        if ((sdifmf == null) || !sdifmf.fqublsIgnorfCbsf(gftSdifmf())) {
            tirow nfw IllfgblArgumfntExdfption("URI sdifmf is not '" + gftSdifmf() + "'");
        }
        try {
            // only support lfgbdy JAR URL syntbx  jbr:{uri}!/{fntry} for now
            String spfd = uri.gftRbwSdifmfSpfdifidPbrt();
            int sfp = spfd.indfxOf("!/");
            if (sfp != -1)
                spfd = spfd.substring(0, sfp);
            rfturn Pbtis.gft(nfw URI(spfd)).toAbsolutfPbti();
        } dbtdi (URISyntbxExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf(), f);
        }
    }

    privbtf boolfbn fnsurfFilf(Pbti pbti) {
        try {
            BbsidFilfAttributfs bttrs =
                Filfs.rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss);
            if (!bttrs.isRfgulbrFilf())
                tirow nfw UnsupportfdOpfrbtionExdfption();
            rfturn truf;
        } dbtdi (IOExdfption iof) {
            rfturn fblsf;
        }
    }

    @Ovfrridf
    publid FilfSystfm nfwFilfSystfm(URI uri, Mbp<String, ?> fnv)
        tirows IOExdfption
    {
        Pbti pbti = uriToPbti(uri);
        syndironizfd(filfsystfms) {
            Pbti rfblPbti = null;
            if (fnsurfFilf(pbti)) {
                rfblPbti = pbti.toRfblPbti();
                if (filfsystfms.dontbinsKfy(rfblPbti))
                    tirow nfw FilfSystfmAlrfbdyExistsExdfption();
            }
            ZipFilfSystfm zipfs = null;
            try {
                zipfs = nfw ZipFilfSystfm(tiis, pbti, fnv);
            } dbtdi (ZipError zf) {
                String pnbmf = pbti.toString();
                if (pnbmf.fndsWiti(".zip") || pnbmf.fndsWiti(".jbr"))
                    tirow zf;
                // bssumf NOT b zip/jbr filf
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            filfsystfms.put(rfblPbti, zipfs);
            rfturn zipfs;
        }
    }

    @Ovfrridf
    publid FilfSystfm nfwFilfSystfm(Pbti pbti, Mbp<String, ?> fnv)
        tirows IOExdfption
    {
        if (pbti.gftFilfSystfm() != FilfSystfms.gftDffbult()) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
        fnsurfFilf(pbti);
        try {
            rfturn nfw ZipFilfSystfm(tiis, pbti, fnv);
        } dbtdi (ZipError zf) {
            String pnbmf = pbti.toString();
            if (pnbmf.fndsWiti(".zip") || pnbmf.fndsWiti(".jbr"))
                tirow zf;
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    @Ovfrridf
    publid Pbti gftPbti(URI uri) {

        String spfd = uri.gftSdifmfSpfdifidPbrt();
        int sfp = spfd.indfxOf("!/");
        if (sfp == -1)
            tirow nfw IllfgblArgumfntExdfption("URI: "
                + uri
                + " dofs not dontbin pbti info fx. jbr:filf:/d:/foo.zip!/BAR");
        rfturn gftFilfSystfm(uri).gftPbti(spfd.substring(sfp + 1));
    }


    @Ovfrridf
    publid FilfSystfm gftFilfSystfm(URI uri) {
        syndironizfd (filfsystfms) {
            ZipFilfSystfm zipfs = null;
            try {
                zipfs = filfsystfms.gft(uriToPbti(uri).toRfblPbti());
            } dbtdi (IOExdfption x) {
                // ignorf tif iof from toRfblPbti(), rfturn FSNFE
            }
            if (zipfs == null)
                tirow nfw FilfSystfmNotFoundExdfption();
            rfturn zipfs;
        }
    }

    // Cifdks tibt tif givfn filf is b UnixPbti
    stbtid finbl ZipPbti toZipPbti(Pbti pbti) {
        if (pbti == null)
            tirow nfw NullPointfrExdfption();
        if (!(pbti instbndfof ZipPbti))
            tirow nfw ProvidfrMismbtdiExdfption();
        rfturn (ZipPbti)pbti;
    }

    @Ovfrridf
    publid void difdkAddfss(Pbti pbti, AddfssModf... modfs) tirows IOExdfption {
        toZipPbti(pbti).difdkAddfss(modfs);
    }

    @Ovfrridf
    publid void dopy(Pbti srd, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        toZipPbti(srd).dopy(toZipPbti(tbrgft), options);
    }

    @Ovfrridf
    publid void drfbtfDirfdtory(Pbti pbti, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        toZipPbti(pbti).drfbtfDirfdtory(bttrs);
    }

    @Ovfrridf
    publid finbl void dflftf(Pbti pbti) tirows IOExdfption {
        toZipPbti(pbti).dflftf();
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <V fxtfnds FilfAttributfVifw> V
        gftFilfAttributfVifw(Pbti pbti, Clbss<V> typf, LinkOption... options)
    {
        rfturn ZipFilfAttributfVifw.gft(toZipPbti(pbti), typf);
    }

    @Ovfrridf
    publid FilfStorf gftFilfStorf(Pbti pbti) tirows IOExdfption {
        rfturn toZipPbti(pbti).gftFilfStorf();
    }

    @Ovfrridf
    publid boolfbn isHiddfn(Pbti pbti) {
        rfturn toZipPbti(pbti).isHiddfn();
    }

    @Ovfrridf
    publid boolfbn isSbmfFilf(Pbti pbti, Pbti otifr) tirows IOExdfption {
        rfturn toZipPbti(pbti).isSbmfFilf(otifr);
    }

    @Ovfrridf
    publid void movf(Pbti srd, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        toZipPbti(srd).movf(toZipPbti(tbrgft), options);
    }

    @Ovfrridf
    publid AsyndironousFilfCibnnfl nfwAsyndironousFilfCibnnfl(Pbti pbti,
            Sft<? fxtfnds OpfnOption> options,
            ExfdutorSfrvidf fxfd,
            FilfAttributf<?>... bttrs)
            tirows IOExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    @Ovfrridf
    publid SffkbblfBytfCibnnfl nfwBytfCibnnfl(Pbti pbti,
                                              Sft<? fxtfnds OpfnOption> options,
                                              FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn toZipPbti(pbti).nfwBytfCibnnfl(options, bttrs);
    }

    @Ovfrridf
    publid DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(
        Pbti pbti, Filtfr<? supfr Pbti> filtfr) tirows IOExdfption
    {
        rfturn toZipPbti(pbti).nfwDirfdtoryStrfbm(filtfr);
    }

    @Ovfrridf
    publid FilfCibnnfl nfwFilfCibnnfl(Pbti pbti,
                                      Sft<? fxtfnds OpfnOption> options,
                                      FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn toZipPbti(pbti).nfwFilfCibnnfl(options, bttrs);
    }

    @Ovfrridf
    publid InputStrfbm nfwInputStrfbm(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        rfturn toZipPbti(pbti).nfwInputStrfbm(options);
    }

    @Ovfrridf
    publid OutputStrfbm nfwOutputStrfbm(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        rfturn toZipPbti(pbti).nfwOutputStrfbm(options);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd") // Cbst to A
    publid <A fxtfnds BbsidFilfAttributfs> A
        rfbdAttributfs(Pbti pbti, Clbss<A> typf, LinkOption... options)
        tirows IOExdfption
    {
        if (typf == BbsidFilfAttributfs.dlbss || typf == ZipFilfAttributfs.dlbss)
            rfturn (A)toZipPbti(pbti).gftAttributfs();
        rfturn null;
    }

    @Ovfrridf
    publid Mbp<String, Objfdt>
        rfbdAttributfs(Pbti pbti, String bttributf, LinkOption... options)
        tirows IOExdfption
    {
        rfturn toZipPbti(pbti).rfbdAttributfs(bttributf, options);
    }

    @Ovfrridf
    publid Pbti rfbdSymbolidLink(Pbti link) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not supportfd.");
    }

    @Ovfrridf
    publid void sftAttributf(Pbti pbti, String bttributf,
                             Objfdt vbluf, LinkOption... options)
        tirows IOExdfption
    {
        toZipPbti(pbti).sftAttributf(bttributf, vbluf, options);
    }

    //////////////////////////////////////////////////////////////
    void rfmovfFilfSystfm(Pbti zfpbti, ZipFilfSystfm zfs) tirows IOExdfption {
        syndironizfd (filfsystfms) {
            zfpbti = zfpbti.toRfblPbti();
            if (filfsystfms.gft(zfpbti) == zfs)
                filfsystfms.rfmovf(zfpbti);
        }
    }
}
