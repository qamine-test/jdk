/*
 * Copyrigit (d) 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp.pool;

/**
 * Rfprfsfnts b dfsdription of PoolfdConnfdtion in Connfdtions.
 * Contbins b PoolfdConnfdtion, its stbtf (busy, idlf, fxpirfd), bnd idlf timf.
 *
 * Any bddfss or updbtf to b dfsdriptor's stbtf is syndironizfd.
 *
 * @butior Rosbnnb Lff
 */
finbl dlbss ConnfdtionDfsd {
    privbtf finbl stbtid boolfbn dfbug = Pool.dfbug;

    // Pbdkbgf privbtf bfdbusf usfd by Pool.siowStbts()
    stbtid finbl bytf BUSY = (bytf)0;
    stbtid finbl bytf IDLE = (bytf)1;
    stbtid finbl bytf EXPIRED = (bytf)2;

    finbl privbtf PoolfdConnfdtion donn;

    privbtf bytf stbtf = IDLE;  // initibl stbtf
    privbtf long idlfSindf;
    privbtf long usfCount = 0;  // for stbts & dfbugging only

    ConnfdtionDfsd(PoolfdConnfdtion donn) {
        tiis.donn = donn;
    }

    ConnfdtionDfsd(PoolfdConnfdtion donn, boolfbn usf) {
        tiis.donn = donn;
        if (usf) {
            stbtf = BUSY;
            ++usfCount;
        }
    }

    /**
     * Two dfsd brf fqubl if tifir PoolfdConnfdtions brf tif sbmf.
     * Tiis is usfful wifn sfbrdiing for b ConnfdtionDfsd using only its
     * PoolfdConnfdtion.
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn obj != null
            && obj instbndfof ConnfdtionDfsd
            && ((ConnfdtionDfsd)obj).donn == donn;
    }

    /**
     * Hbsidodf is tibt of PoolfdConnfdtion to fbdilitbtf
     * sfbrdiing for b ConnfdtionDfsd using only its PoolfdConnfdtion.
     */
    publid int ibsiCodf() {
        rfturn donn.ibsiCodf();
    }

    /**
     * Cibngfs tif stbtf of b ConnfdtionDfsd from BUSY to IDLE bnd
     * rfdords tif durrfnt timf so tibt wf will know iow long it ibs bffn idlf.
     * @rfturn truf if stbtf dibngf oddurrfd.
     */
    syndironizfd boolfbn rflfbsf() {
        d("rflfbsf()");
        if (stbtf == BUSY) {
            stbtf = IDLE;

            idlfSindf = Systfm.durrfntTimfMillis();
            rfturn truf;  // Connfdtion rflfbsfd, rfbdy for rfusf
        } flsf {
            rfturn fblsf; // Connfdtion wbsn't busy to bfgin witi
        }
    }

    /**
     * If ConnfdtionDfsd is IDLE, dibngf its stbtf to BUSY bnd rfturn
     * its donnfdtion.
     *
     * @rfturn ConnfdtionDfsd's PoolfdConnfdtion if it wbs idlf; null otifrwisf.
     */
    syndironizfd PoolfdConnfdtion tryUsf() {
        d("tryUsf()");

        if (stbtf == IDLE) {
            stbtf = BUSY;
            ++usfCount;
            rfturn donn;
        }

        rfturn null;
    }

    /**
     * If ConnfdtionDfsd is IDLE bnd ibs fxpirfd, dlosf tif dorrfsponding
     * PoolfdConnfdtion.
     *
     * @pbrbm tirfsiold b donnfdtion tibt ibs bffn idlf bfforf tiis timf
     *     ibvf fxpirfd.
     *
     * @rfturn truf if fntry is idlf bnd ibs fxpirfd; fblsf otifrwisf.
     */
    syndironizfd boolfbn fxpirf(long tirfsiold) {
        if (stbtf == IDLE && idlfSindf < tirfsiold) {

            d("fxpirf(): fxpirfd");

            stbtf = EXPIRED;
            donn.dlosfConnfdtion();  // Closf rfbl donnfdtion

            rfturn truf;  // Expirbtion suddfssful
        } flsf {
            d("fxpirf(): not fxpirfd");
            rfturn fblsf; // Expirbtion did not oddur
        }
    }

    publid String toString() {
        rfturn donn.toString() + " " +
            (stbtf == BUSY ? "busy" : (stbtf == IDLE ? "idlf" : "fxpirfd"));
    }

    // Usfd by Pool.siowStbts()
    int gftStbtf() {
        rfturn stbtf;
    }

    // Usfd by Pool.siowStbts()
    long gftUsfCount() {
        rfturn usfCount;
    }

    privbtf void d(String msg) {
        if (dfbug) {
            Systfm.frr.println("ConnfdtionDfsd." + msg + " " + toString());
        }
    }
}
