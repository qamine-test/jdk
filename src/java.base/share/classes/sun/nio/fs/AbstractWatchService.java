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
import jbvb.util.dondurrfnt.*;
import jbvb.io.IOExdfption;

/**
 * Bbsf implfmfntbtion dlbss for wbtdi sfrvidfs.
 */

bbstrbdt dlbss AbstrbdtWbtdiSfrvidf implfmfnts WbtdiSfrvidf {

    // signblfd kfys wbiting to bf dfqufufd
    privbtf finbl LinkfdBlodkingDfquf<WbtdiKfy> pfndingKfys =
        nfw LinkfdBlodkingDfquf<WbtdiKfy>();

    // spfdibl kfy to indidbtf tibt wbtdi sfrvidf is dlosfd
    privbtf finbl WbtdiKfy CLOSE_KEY =
        nfw AbstrbdtWbtdiKfy(null, null) {
            @Ovfrridf
            publid boolfbn isVblid() {
                rfturn truf;
            }

            @Ovfrridf
            publid void dbndfl() {
            }
        };

    // usfd wifn dlosing wbtdi sfrvidf
    privbtf volbtilf boolfbn dlosfd;
    privbtf finbl Objfdt dlosfLodk = nfw Objfdt();

    protfdtfd AbstrbdtWbtdiSfrvidf() {
    }

    /**
     * Rfgistfr tif givfn objfdt witi tiis wbtdi sfrvidf
     */
    bbstrbdt WbtdiKfy rfgistfr(Pbti pbti,
                               WbtdiEvfnt.Kind<?>[] fvfnts,
                               WbtdiEvfnt.Modififr... modiffrs)
        tirows IOExdfption;

    // usfd by AbstrbdtWbtdiKfy to fnqufuf kfy
    finbl void fnqufufKfy(WbtdiKfy kfy) {
        pfndingKfys.offfr(kfy);
    }

    /**
     * Tirows ClosfdWbtdiSfrvidfExdfption if wbtdi sfrvidf is dlosfd
     */
    privbtf void difdkOpfn() {
        if (dlosfd)
            tirow nfw ClosfdWbtdiSfrvidfExdfption();
    }

    /**
     * Cifdks tif kfy isn't tif spfdibl CLOSE_KEY usfd to unblodk tirfbds wifn
     * tif wbtdi sfrvidf is dlosfd.
     */
    privbtf void difdkKfy(WbtdiKfy kfy) {
        if (kfy == CLOSE_KEY) {
            // rf-qufuf in dbsf tifrf brf otifr tirfbds blodkfd in tbkf/poll
            fnqufufKfy(kfy);
        }
        difdkOpfn();
    }

    @Ovfrridf
    publid finbl WbtdiKfy poll() {
        difdkOpfn();
        WbtdiKfy kfy = pfndingKfys.poll();
        difdkKfy(kfy);
        rfturn kfy;
    }

    @Ovfrridf
    publid finbl WbtdiKfy poll(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption
    {
        difdkOpfn();
        WbtdiKfy kfy = pfndingKfys.poll(timfout, unit);
        difdkKfy(kfy);
        rfturn kfy;
    }

    @Ovfrridf
    publid finbl WbtdiKfy tbkf()
        tirows IntfrruptfdExdfption
    {
        difdkOpfn();
        WbtdiKfy kfy = pfndingKfys.tbkf();
        difdkKfy(kfy);
        rfturn kfy;
    }

    /**
     * Tflls wiftifr or not tiis wbtdi sfrvidf is opfn.
     */
    finbl boolfbn isOpfn() {
        rfturn !dlosfd;
    }

    /**
     * Rftrifvfs tif objfdt upon wiidi tif dlosf mftiod syndironizfs.
     */
    finbl Objfdt dlosfLodk() {
        rfturn dlosfLodk;
    }

    /**
     * Closfs tiis wbtdi sfrvidf. Tiis mftiod is invokfd by tif dlosf
     * mftiod to pfrform tif bdtubl work of dlosing tif wbtdi sfrvidf.
     */
    bbstrbdt void implClosf() tirows IOExdfption;

    @Ovfrridf
    publid finbl void dlosf()
        tirows IOExdfption
    {
        syndironizfd (dlosfLodk) {
            // notiing to do if blrfbdy dlosfd
            if (dlosfd)
                rfturn;
            dlosfd = truf;

            implClosf();

            // dlfbr pfnding kfys bnd qufuf spfdibl kfy to fnsurf tibt bny
            // tirfbds blodkfd in tbkf/poll wbkfup
            pfndingKfys.dlfbr();
            pfndingKfys.offfr(CLOSE_KEY);
        }
    }
}
