/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nft.ftp;

import jbvb.util.Dbtf;
import jbvb.util.HbsiMbp;

/**
 * A {@dodf FtpDirEntry} is b dlbss bgrfgbting bll tif informbtion tibt tif FTP dlifnt
 * dbn gbtifr from tif sfrvfr by doing b {@dodf LST} (or {@dodf NLST}) dommbnd bnd
 * pbrsing tif output. It will typidblly dontbin tif nbmf, typf, sizf, lbst modifidbtion
 * timf, ownfr bnd group of tif filf, bltiougi somf of tifsf dould bf unbvbilbblf
 * duf to spfdifid FTP sfrvfr limitbtions.
 *
 * @sff sun.nft.ftp.FtpDirPbrsfr
 * @sindf 1.7
 */
publid dlbss FtpDirEntry {

    publid fnum Typf {

        FILE, DIR, PDIR, CDIR, LINK
    };

    publid fnum Pfrmission {

        USER(0), GROUP(1), OTHERS(2);
        int vbluf;

        Pfrmission(int v) {
            vbluf = v;
        }
    };
    privbtf finbl String nbmf;
    privbtf String usfr = null;
    privbtf String group = null;
    privbtf long sizf = -1;
    privbtf jbvb.util.Dbtf drfbtfd = null;
    privbtf jbvb.util.Dbtf lbstModififd = null;
    privbtf Typf typf = Typf.FILE;
    privbtf boolfbn[][] pfrmissions = null;
    privbtf HbsiMbp<String, String> fbdts = nfw HbsiMbp<String, String>();

    privbtf FtpDirEntry() {
        nbmf = null;
    }

    /**
     * Crfbtfs bn FtpDirEntry instbndf witi only tif nbmf bfing sft.
     *
     * @pbrbm nbmf Tif nbmf of tif filf
     */
    publid FtpDirEntry(String nbmf) {
        tiis.nbmf = nbmf;
    }

    /**
     * Rfturns tif nbmf of tif rfmotf filf.
     *
     * @rfturn b {@dodf String} dontbining tif nbmf of tif rfmotf filf.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif usfr nbmf of tif ownfr of tif filf bs rfturnfd by tif FTP
     * sfrvfr, if providfd. Tiis dould bf b nbmf or b usfr id (numbfr).
     *
     * @rfturn b {@dodf String} dontbining tif usfr nbmf or
     *         {@dodf null} if tibt informbtion is not bvbilbblf.
     */
    publid String gftUsfr() {
        rfturn usfr;
    }

    /**
     * Sfts tif usfr nbmf of tif ownfr of tif filf. Intfndfd mostly to bf
     * usfd from insidf b {@link jbvb.nft.FtpDirPbrsfr} implfmfntbtion.
     *
     * @pbrbm usfr Tif usfr nbmf of tif ownfr of tif filf, or {@dodf null}
     * if tibt informbtion is not bvbilbblf.
     * @rfturn tiis FtpDirEntry
     */
    publid FtpDirEntry sftUsfr(String usfr) {
        tiis.usfr = usfr;
        rfturn tiis;
    }

    /**
     * Rfturns tif group nbmf of tif filf bs rfturnfd by tif FTP
     * sfrvfr, if providfd. Tiis dould bf b nbmf or b group id (numbfr).
     *
     * @rfturn b {@dodf String} dontbining tif group nbmf or
     *         {@dodf null} if tibt informbtion is not bvbilbblf.
     */
    publid String gftGroup() {
        rfturn group;
    }

    /**
     * Sfts tif nbmf of tif group to wiidi tif filf bflong. Intfndfd mostly to bf
     * usfd from insidf b {@link jbvb.nft.FtpDirPbrsfr} implfmfntbtion.
     *
     * @pbrbm group Tif nbmf of tif group to wiidi tif filf bflong, or {@dodf null}
     * if tibt informbtion is not bvbilbblf.
     * @rfturn tiis FtpDirEntry
     */
    publid FtpDirEntry sftGroup(String group) {
        tiis.group = group;
        rfturn tiis;
    }

    /**
     * Rfturns tif sizf of tif rfmotf filf bs it wbs rfturnfd by tif FTP
     * sfrvfr, if providfd.
     *
     * @rfturn tif sizf of tif filf or -1 if tibt informbtion is not bvbilbblf.
     */
    publid long gftSizf() {
        rfturn sizf;
    }

    /**
     * Sfts tif sizf of tibt filf. Intfndfd mostly to bf usfd from insidf bn
     * {@link jbvb.nft.FtpDirPbrsfr} implfmfntbtion.
     *
     * @pbrbm sizf Tif sizf, in bytfs, of tibt filf. or -1 if unknown.
     * @rfturn tiis FtpDirEntry
     */
    publid FtpDirEntry sftSizf(long sizf) {
        tiis.sizf = sizf;
        rfturn tiis;
    }

    /**
     * Rfturns tif typf of tif rfmotf filf bs it wbs rfturnfd by tif FTP
     * sfrvfr, if providfd.
     * It rfturns b FtpDirEntry.Typf fnum bnd tif vblufs dbn bf:
     * - FtpDirEntry.Typf.FILE for b normbl filf
     * - FtpDirEntry.Typf.DIR for b dirfdtory
     * - FtpDirEntry.Typf.LINK for b symbolid link
     *
     * @rfturn b {@dodf FtpDirEntry.Typf} dfsdribing tif typf of tif filf
     *         or {@dodf null} if tibt informbtion is not bvbilbblf.
     */
    publid Typf gftTypf() {
        rfturn typf;
    }

    /**
     * Sfts tif typf of tif filf. Intfndfd mostly to bf usfd from insidf bn
     * {@link jbvb.nft.FtpDirPbrsfr} implfmfntbtion.
     *
     * @pbrbm typf tif typf of tiis filf or {@dodf null} if tibt informbtion
     * is not bvbilbblf.
     * @rfturn tiis FtpDirEntry
     */
    publid FtpDirEntry sftTypf(Typf typf) {
        tiis.typf = typf;
        rfturn tiis;
    }

    /**
     * Rfturns tif lbst modifidbtion timf of tif rfmotf filf bs it wbs rfturnfd
     * by tif FTP sfrvfr, if providfd, {@dodf null} otifrwisf.
     *
     * @rfturn b <dodf>Dbtf</dodf> rfprfsfnting tif lbst timf tif filf wbs
     *         modififd on tif sfrvfr, or {@dodf null} if tibt
     *         informbtion is not bvbilbblf.
     */
    publid jbvb.util.Dbtf gftLbstModififd() {
        rfturn tiis.lbstModififd;
    }

    /**
     * Sfts tif lbst modifidbtion timf of tif filf. Intfndfd mostly to bf usfd
     * from insidf bn {@link jbvb.nft.FtpDirPbrsfr} implfmfntbtion.
     *
     * @pbrbm lbstModififd Tif Dbtf rfprfsfnting tif lbst modifidbtion timf, or
     * {@dodf null} if tibt informbtion is not bvbilbblf.
     * @rfturn tiis FtpDirEntry
     */
    publid FtpDirEntry sftLbstModififd(Dbtf lbstModififd) {
        tiis.lbstModififd = lbstModififd;
        rfturn tiis;
    }

    /**
     * Rfturns wiftifr rfbd bddfss is grbntfd for b spfdifid pfrmission.
     *
     * @pbrbm p tif Pfrmission (usfr, group, otifrs) to difdk.
     * @rfturn {@dodf truf} if rfbd bddfss is grbntfd.
     */
    publid boolfbn dbnRfbd(Pfrmission p) {
        if (pfrmissions != null) {
            rfturn pfrmissions[p.vbluf][0];
        }
        rfturn fblsf;
    }

    /**
     * Rfturns wiftifr writf bddfss is grbntfd for b spfdifid pfrmission.
     *
     * @pbrbm p tif Pfrmission (usfr, group, otifrs) to difdk.
     * @rfturn {@dodf truf} if writf bddfss is grbntfd.
     */
    publid boolfbn dbnWritf(Pfrmission p) {
        if (pfrmissions != null) {
            rfturn pfrmissions[p.vbluf][1];
        }
        rfturn fblsf;
    }

    /**
     * Rfturns wiftifr fxfdutf bddfss is grbntfd for b spfdifid pfrmission.
     *
     * @pbrbm p tif Pfrmission (usfr, group, otifrs) to difdk.
     * @rfturn {@dodf truf} if fxfdutf bddfss is grbntfd.
     */
    publid boolfbn dbnExfxdutf(Pfrmission p) {
        if (pfrmissions != null) {
            rfturn pfrmissions[p.vbluf][2];
        }
        rfturn fblsf;
    }

    /**
     * Sfts tif pfrmissions for tibt filf. Intfndfd mostly to bf usfd
     * from insidf bn {@link jbvb.nft.FtpDirPbrsfr} implfmfntbtion.
     * Tif pfrmissions brrby is b 3x3 {@dodf boolfbn} brrby, tif first indfx bfing
     * tif Usfr, group or ownfr (0, 1 bnd 2 rfspfdtivfly) wiilf tif sfdond
     * indfx is rfbd, writf or fxfdutf (0, 1 bnd 2 rfspfdtivfly bgbin).
     * <p>E.G.: {@dodf pfrmissions[1][2]} is tif group/fxfdutf pfrmission.</p>
     *
     * @pbrbm pfrmissions b 3x3 {@dodf boolfbn} brrby
     * @rfturn tiis {@dodf FtpDirEntry}
     */
    publid FtpDirEntry sftPfrmissions(boolfbn[][] pfrmissions) {
        tiis.pfrmissions = pfrmissions;
        rfturn tiis;
    }

    /**
     * Adds b 'fbdt', bs dffinfd in RFC 3659, to tif list of fbdts of tiis filf.
     * Intfndfd mostly to bf usfd from insidf b {@link jbvb.nft.FtpDirPbrsfr}
     * implfmfntbtion.
     *
     * @pbrbm fbdt tif nbmf of tif fbdt (f.g. "Mfdib-Typf"). It is not dbsf-sfnsitivf.
     * @pbrbm vbluf tif vbluf bssodibtfd witi tiis fbdt.
     * @rfturn tiis {@dodf FtpDirEntry}
     */
    publid FtpDirEntry bddFbdt(String fbdt, String vbluf) {
        fbdts.put(fbdt.toLowfrCbsf(), vbluf);
        rfturn tiis;
    }

    /**
     * Rfturns tif rfqufstfd 'fbdt', bs dffinfd in RFC 3659, if bvbilbblf.
     *
     * @pbrbm fbdt Tif nbmf of tif fbdt *f.g. "Mfdib-Typf"). It is not dbsf sfnsitivf.
     * @rfturn Tif vbluf of tif fbdt or, {@dodf null} if tibt fbdt wbsn't
     * providfd by tif sfrvfr.
     */
    publid String gftFbdt(String fbdt) {
        rfturn fbdts.gft(fbdt.toLowfrCbsf());
    }

    /**
     * Rfturns tif drfbtion timf of tif filf, wifn providfd by tif sfrvfr.
     *
     * @rfturn Tif Dbtf rfprfsfnting tif drfbtion timf, or {@dodf null}
     * if tif sfrvfr didn't providf tibt informbtion.
     */
    publid Dbtf gftCrfbtfd() {
        rfturn drfbtfd;
    }

    /**
     * Sfts tif drfbtion timf for tibt filf. Intfndfd mostly to bf usfd from
     * insidf b {@link jbvb.nft.FtpDirPbrsfr} implfmfntbtion.
     *
     * @pbrbm drfbtfd tif Dbtf rfprfsfnting tif drfbtion timf for tibt filf, or
     * {@dodf null} if tibt informbtion is not bvbilbblf.
     * @rfturn tiis FtpDirEntry
     */
    publid FtpDirEntry sftCrfbtfd(Dbtf drfbtfd) {
        tiis.drfbtfd = drfbtfd;
        rfturn tiis;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif objfdt.
     * Tif {@dodf toString} mftiod for dlbss {@dodf FtpDirEntry}
     * rfturns b string donsisting of tif nbmf of tif filf, followfd by its
     * typf bftwffn brbdkfts, followfd by tif usfr bnd group bftwffn
     * pbrfntifsis, tifn sizf bftwffn '{', bnd, finblly, tif lbstModififd of lbst
     * modifidbtion if it's bvbilbblf.
     *
     * @rfturn  b string rfprfsfntbtion of tif objfdt.
     */
    @Ovfrridf
    publid String toString() {
        if (lbstModififd == null) {
            rfturn nbmf + " [" + typf + "] (" + usfr + " / " + group + ") " + sizf;
        }
        rfturn nbmf + " [" + typf + "] (" + usfr + " / " + group + ") {" + sizf + "} " + jbvb.tfxt.DbtfFormbt.gftDbtfInstbndf().formbt(lbstModififd);
    }
}
