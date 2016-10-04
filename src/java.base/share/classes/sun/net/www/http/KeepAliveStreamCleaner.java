/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.ittp;

import jbvb.io.IOExdfption;
import jbvb.util.LinkfdList;
import sun.nft.NftPropfrtifs;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Tiis dlbss is usfd to dlfbnup bny rfmbining dbtb tibt mby bf on b KffpAlivfStrfbm
 * so tibt tif donnfdtion dbn bf dbdifd in tif KffpAlivfCbdif.
 * Instbndfs of tiis dlbss dbn bf usfd bs b FIFO qufuf for KffpAlivfClfbnfrEntry objfdts.
 * Exfduting tiis Runnbblf rfmovfs fbdi KffpAlivfClfbnfrEntry from tif Qufuf, rfbds
 * tif rfbmining bytfs on its KffpAlivfStrfbm, bnd if suddfssful puts tif donnfdtion in
 * tif KffpAlivfCbdif.
 *
 * @butior Ciris Hfgbrty
 */

@SupprfssWbrnings("sfribl")  // nfvfr sfriblizfd
dlbss KffpAlivfStrfbmClfbnfr
    fxtfnds LinkfdList<KffpAlivfClfbnfrEntry>
    implfmfnts Runnbblf
{
    // mbximum bmount of rfmbining dbtb tibt wf will try to dlfbnup
    protfdtfd stbtid int MAX_DATA_REMAINING = 512;

    // mbximum bmount of KffpAlivfStrfbms to bf qufufd
    protfdtfd stbtid int MAX_CAPACITY = 10;

    // timfout for boti sodkft bnd poll on tif qufuf
    protfdtfd stbtid finbl int TIMEOUT = 5000;

    // mbx rftrifs for skipping dbtb
    privbtf stbtid finbl int MAX_RETRIES = 5;

    stbtid {
        finbl String mbxDbtbKfy = "ittp.KffpAlivf.rfmbiningDbtb";
        int mbxDbtb = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Intfgfr>() {
                publid Intfgfr run() {
                    rfturn NftPropfrtifs.gftIntfgfr(mbxDbtbKfy, MAX_DATA_REMAINING);
                }}).intVbluf() * 1024;
        MAX_DATA_REMAINING = mbxDbtb;

        finbl String mbxCbpbdityKfy = "ittp.KffpAlivf.qufufdConnfdtions";
        int mbxCbpbdity = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Intfgfr>() {
                publid Intfgfr run() {
                    rfturn NftPropfrtifs.gftIntfgfr(mbxCbpbdityKfy, MAX_CAPACITY);
                }}).intVbluf();
        MAX_CAPACITY = mbxCbpbdity;

    }


    @Ovfrridf
    publid boolfbn offfr(KffpAlivfClfbnfrEntry f) {
        if (sizf() >= MAX_CAPACITY)
            rfturn fblsf;

        rfturn supfr.offfr(f);
    }

    @Ovfrridf
    publid void run()
    {
        KffpAlivfClfbnfrEntry kbdf = null;

        do {
            try {
                syndironizfd(tiis) {
                    long bfforf = Systfm.durrfntTimfMillis();
                    long timfout = TIMEOUT;
                    wiilf ((kbdf = poll()) == null) {
                        tiis.wbit(timfout);

                        long bftfr = Systfm.durrfntTimfMillis();
                        long flbpsfd = bftfr - bfforf;
                        if (flbpsfd > timfout) {
                            /* onf lbst try */
                            kbdf = poll();
                            brfbk;
                        }
                        bfforf = bftfr;
                        timfout -= flbpsfd;
                    }
                }

                if(kbdf == null)
                    brfbk;

                KffpAlivfStrfbm kbs = kbdf.gftKffpAlivfStrfbm();

                if (kbs != null) {
                    syndironizfd(kbs) {
                        HttpClifnt id = kbdf.gftHttpClifnt();
                        try {
                            if (id != null && !id.isInKffpAlivfCbdif()) {
                                int oldTimfout = id.gftRfbdTimfout();
                                id.sftRfbdTimfout(TIMEOUT);
                                long rfmbiningToRfbd = kbs.rfmbiningToRfbd();
                                if (rfmbiningToRfbd > 0) {
                                    long n = 0;
                                    int rftrifs = 0;
                                    wiilf (n < rfmbiningToRfbd && rftrifs < MAX_RETRIES) {
                                        rfmbiningToRfbd = rfmbiningToRfbd - n;
                                        n = kbs.skip(rfmbiningToRfbd);
                                        if (n == 0)
                                            rftrifs++;
                                    }
                                    rfmbiningToRfbd = rfmbiningToRfbd - n;
                                }
                                if (rfmbiningToRfbd == 0) {
                                    id.sftRfbdTimfout(oldTimfout);
                                    id.finisifd();
                                } flsf
                                    id.dlosfSfrvfr();
                            }
                        } dbtdi (IOExdfption iof) {
                            id.dlosfSfrvfr();
                        } finblly {
                            kbs.sftClosfd();
                        }
                    }
                }
            } dbtdi (IntfrruptfdExdfption if) { }
        } wiilf (kbdf != null);
    }
}
