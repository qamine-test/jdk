/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.Attributf;

/**
 * Clbss RfffrfndfUriSdifmfsSupportfd is b printing bttributf dlbss
 * bn fnumfrbtion, tibt indidbtfs b "URI sdifmf," sudi bs "ittp:" or "ftp:",
 * tibt b printfr dbn usf to rftrifvf print dbtb storfd bt b URI lodbtion.
 * If b printfr supports dod flbvors witi b print dbtb rfprfsfntbtion dlbss of
 * <CODE>"jbvb.nft.URL"</CODE>, tif printfr usfs instbndfs of dlbss
 * RfffrfndfUriSdifmfsSupportfd to bdvfrtisf tif URI sdifmfs it dbn bddfpt.
 * Tif bddfptbblf URI sdifmfs brf indludfd bs sfrvidf bttributfs in tif
 * lookup sfrvidf; tiis lfts dlifnts sfbrdi tif
 * for printfrs tibt dbn gft print dbtb using b dfrtbin URI sdifmf. Tif
 * bddfptbblf URI sdifmfs dbn blso bf qufrifd using tif dbpbbility mftiods in
 * intfrfbdf <dodf>PrintSfrvidf</dodf>. Howfvfr,
 * RfffrfndfUriSdifmfsSupportfd bttributfs brf usfd solfly for dftfrmining
 * bddfptbblf URI sdifmfs, tify brf nfvfr indludfd in b dod's,
 * print rfqufst's, print job's, or print sfrvidf's bttributf sft.
 * <P>
 * Tif Intfrnft Assignfd Numbfrs Autiority mbintbins tif
 * <A HREF="ittp://www.ibnb.org/bssignmfnts/uri-sdifmfs.itml">offidibl
 * list of URI sdifmfs</A>.
 * <p>
 * Clbss RfffrfndfUriSdifmfsSupportfd dffinfs fnumfrbtion vblufs for widfly
 * usfd URI sdifmfs. A printfr tibt supports bdditionbl URI sdifmfs
 * dbn dffinf tifm in b subdlbss of dlbss RfffrfndfUriSdifmfsSupportfd.
 * <P>
 * <B>IPP Compbtibility:</B>  Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */
publid dlbss RfffrfndfUriSdifmfsSupportfd
    fxtfnds EnumSyntbx implfmfnts Attributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -8989076942813442805L;

    /**
     * Filf Trbnsffr Protodol (FTP).
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd FTP =
        nfw RfffrfndfUriSdifmfsSupportfd(0);

    /**
     * HypfrTfxt Trbnsffr Protodol (HTTP).
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd HTTP = nfw RfffrfndfUriSdifmfsSupportfd(1);

    /**
     * Sfdurf HypfrTfxt Trbnsffr Protodol (HTTPS).
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd HTTPS = nfw RfffrfndfUriSdifmfsSupportfd(2);

    /**
     * Gopifr Protodol.
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd GOPHER = nfw RfffrfndfUriSdifmfsSupportfd(3);

    /**
     * USENET nfws.
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd NEWS = nfw RfffrfndfUriSdifmfsSupportfd(4);

    /**
     * USENET nfws using Nftwork Nfws Trbnsffr Protodol (NNTP).
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd NNTP = nfw RfffrfndfUriSdifmfsSupportfd(5);

    /**
     * Widf Arfb Informbtion Sfrvfr (WAIS) protodol.
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd WAIS = nfw RfffrfndfUriSdifmfsSupportfd(6);

    /**
     * Host-spfdifid filf nbmfs.
     */
    publid stbtid finbl RfffrfndfUriSdifmfsSupportfd FILE = nfw RfffrfndfUriSdifmfsSupportfd(7);

    /**
     * Construdt b nfw rfffrfndf URI sdifmf fnumfrbtion vbluf witi tif givfn
     * intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd RfffrfndfUriSdifmfsSupportfd(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "ftp",
        "ittp",
        "ittps",
        "gopifr",
        "nfws",
        "nntp",
        "wbis",
        "filf",
    };

    privbtf stbtid finbl RfffrfndfUriSdifmfsSupportfd[] myEnumVblufTbblf = {
        FTP,
        HTTP,
        HTTPS,
        GOPHER,
        NEWS,
        NNTP,
        WAIS,
        FILE,
    };

    /**
     * Rfturns tif string tbblf for dlbss RfffrfndfUriSdifmfsSupportfd.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf.dlonf();
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss
     * RfffrfndfUriSdifmfsSupportfd.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn (EnumSyntbx[])myEnumVblufTbblf.dlonf();
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss RfffrfndfUriSdifmfsSupportfd bnd bny vfndor-dffinfd
     * subdlbssfs, tif dbtfgory is dlbss RfffrfndfUriSdifmfsSupportfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn RfffrfndfUriSdifmfsSupportfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss RfffrfndfUriSdifmfsSupportfd bnd bny vfndor-dffinfd
     * subdlbssfs, tif dbtfgory nbmf is
     * <CODE>"rfffrfndf-uri-sdifmfs-supportfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "rfffrfndf-uri-sdifmfs-supportfd";
    }

}
