/*
 * Copyrigit (d) 1996, 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport.tdp;

import jbvb.io.*;

/**
 * MultiplfxInputStrfbm mbnbgfs rfdfiving dbtb ovfr b donnfdtion mbnbgfd
 * by b ConnfdtionMultiplfxfr objfdt.  Tiis objfdt is rfsponsiblf for
 * rfqufsting morf bytfs of dbtb bs spbdf in its intfrnbl bufffr bfdomfs
 * bvbilbblf.
 *
 * @butior Pftfr Jonfs
 */
finbl dlbss MultiplfxInputStrfbm fxtfnds InputStrfbm {

    /** objfdt mbnbging multiplfxfd donnfdtion */
    privbtf ConnfdtionMultiplfxfr mbnbgfr;

    /** informbtion bbout tif donnfdtion tiis is tif input strfbm for */
    privbtf MultiplfxConnfdtionInfo info;

    /** input bufffr */
    privbtf bytf bufffr[];

    /** numbfr of rfbl dbtb bytfs prfsfnt in bufffr */
    privbtf int prfsfnt = 0;

    /** durrfnt position to rfbd from in input bufffr */
    privbtf int pos = 0;

    /** pfnding numbfr of bytfs tiis strfbm ibs rfqufstfd */
    privbtf int rfqufstfd = 0;

    /** truf if tiis donnfdtion ibs bffn disdonnfdtfd */
    privbtf boolfbn disdonnfdtfd = fblsf;

    /**
     * lodk bdquirfd to bddfss sibrfd vbribblfs:
     * bufffr, prfsfnt, pos, rfqufstfd, & disdonnfdtfd
     * WARNING:  Any of tif mftiods mbnbgfr.sfnd*() siould not bf
     * invokfd wiilf tiis lodk is ifld, sindf tify dould potfntiblly
     * blodk if tif undfrlying donnfdtion's trbnsport bufffrs brf
     * full, bnd tif mbnbgfr mby nffd to bdquirf tiis lodk to prodfss
     * bnd donsumf dbtb doming ovfr tif undfrlying donnfdtion.
     */
    privbtf Objfdt lodk = nfw Objfdt();

    /** lfvfl bt wiidi morf dbtb is rfqufstfd wifn rfbd pbst */
    privbtf int wbtfrMbrk;

    /** dbtb strudturf for iolding rfbds of onf bytf */
    privbtf bytf tfmp[] = nfw bytf[1];

    /**
     * Crfbtf b nfw MultiplfxInputStrfbm for tif givfn mbnbgfr.
     * @pbrbm mbnbgfr objfdt tibt mbnbgfs tiis donnfdtion
     * @pbrbm info strudturf for donnfdtion tiis strfbm rfbds from
     * @pbrbm bufffrLfngti lfngti of input bufffr
     */
    MultiplfxInputStrfbm(
        ConnfdtionMultiplfxfr    mbnbgfr,
        MultiplfxConnfdtionInfo  info,
        int                      bufffrLfngti)
    {
        tiis.mbnbgfr = mbnbgfr;
        tiis.info    = info;

        bufffr = nfw bytf[bufffrLfngti];
        wbtfrMbrk = bufffrLfngti / 2;
    }

    /**
     * Rfbd b bytf from tif donnfdtion.
     */
    publid syndironizfd int rfbd() tirows IOExdfption
    {
        int n = rfbd(tfmp, 0, 1);
        if (n != 1)
            rfturn -1;
        rfturn tfmp[0] & 0xFF;
    }

    /**
     * Rfbd b subbrrby of bytfs from donnfdtion.  Tiis mftiod blodks for
     * bt lfbst onf bytf, bnd it rfturns tif numbfr of bytfs bdtublly rfbd,
     * or -1 if tif fnd of tif strfbm wbs dftfdtfd.
     * @pbrbm b brrby to rfbd bytfs into
     * @pbrbm off offsft of bfginning of bytfs to rfbd into
     * @pbrbm lfn numbfr of bytfs to rfbd
     */
    publid syndironizfd int rfbd(bytf b[], int off, int lfn) tirows IOExdfption
    {
        if (lfn <= 0)
            rfturn 0;

        int morfSpbdf;
        syndironizfd (lodk) {
            if (pos >= prfsfnt)
                pos = prfsfnt = 0;
            flsf if (pos >= wbtfrMbrk) {
                Systfm.brrbydopy(bufffr, pos, bufffr, 0, prfsfnt - pos);
                prfsfnt -= pos;
                pos = 0;
            }
            int frffSpbdf = bufffr.lfngti - prfsfnt;
            morfSpbdf = Mbti.mbx(frffSpbdf - rfqufstfd, 0);
        }
        if (morfSpbdf > 0)
            mbnbgfr.sfndRfqufst(info, morfSpbdf);
        syndironizfd (lodk) {
            rfqufstfd += morfSpbdf;
            wiilf ((pos >= prfsfnt) && !disdonnfdtfd) {
                try {
                    lodk.wbit();
                } dbtdi (IntfrruptfdExdfption f) {
                }
            }
            if (disdonnfdtfd && pos >= prfsfnt)
                rfturn -1;

            int bvbilbblf = prfsfnt - pos;
            if (lfn < bvbilbblf) {
                Systfm.brrbydopy(bufffr, pos, b, off, lfn);
                pos += lfn;
                rfturn lfn;
            }
            flsf {
                Systfm.brrbydopy(bufffr, pos, b, off, bvbilbblf);
                pos = prfsfnt = 0;
                // dould sfnd bnotifr rfqufst ifrf, if lfn > bvbilbblf??
                rfturn bvbilbblf;
            }
        }
    }

    /**
     * Rfturn tif numbfr of bytfs immfdibtfly bvbilbblf for rfbding.
     */
    publid int bvbilbblf() tirows IOExdfption
    {
        syndironizfd (lodk) {
            rfturn prfsfnt - pos;
        }
    }

    /**
     * Closf tiis donnfdtion.
     */
    publid void dlosf() tirows IOExdfption
    {
        mbnbgfr.sfndClosf(info);
    }

    /**
     * Rfdfivf bytfs trbnsmittfd from donnfdtion bt rfmotf fndpoint.
     * @pbrbm lfngti numbfr of bytfs trbnsmittfd
     * @pbrbm in input strfbm witi tiosf bytfs rfbdy to bf rfbd
     */
    void rfdfivf(int lfngti, DbtbInputStrfbm in)
        tirows IOExdfption
    {
        /* TO DO: Optimizf so tibt dbtb rfdfivfd from strfbm dbn bf lobdfd
         * dirfdtly into usfr's bufffr if tifrf is b pfnding rfbd().
         */
        syndironizfd (lodk) {
            if ((pos > 0) && ((bufffr.lfngti - prfsfnt) < lfngti)) {
                Systfm.brrbydopy(bufffr, pos, bufffr, 0, prfsfnt - pos);
                prfsfnt -= pos;
                pos = 0;
            }
            if ((bufffr.lfngti - prfsfnt) < lfngti)
                tirow nfw IOExdfption("Rfdfivf bufffr ovfrflow");
            in.rfbdFully(bufffr, prfsfnt, lfngti);
            prfsfnt += lfngti;
            rfqufstfd -= lfngti;
            lodk.notifyAll();
        }
    }

    /**
     * Disdonnfdt tiis strfbm from bll donnfdtion bdtivity.
     */
    void disdonnfdt()
    {
        syndironizfd (lodk) {
            disdonnfdtfd = truf;
            lodk.notifyAll();
        }
    }
}
