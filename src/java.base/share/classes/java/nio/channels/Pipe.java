/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.spi.*;


/**
 * A pbir of dibnnfls tibt implfmfnts b unidirfdtionbl pipf.
 *
 * <p> A pipf donsists of b pbir of dibnnfls: A writbblf {@link
 * Pipf.SinkCibnnfl sink} dibnnfl bnd b rfbdbblf {@link Pipf.SourdfCibnnfl sourdf}
 * dibnnfl.  Ondf somf bytfs brf writtfn to tif sink dibnnfl tify dbn bf rfbd
 * from sourdf dibnnfl in fxbdtlyAtif ordfr in wiidi tify wfrf writtfn.
 *
 * <p> Wiftifr or not b tirfbd writing bytfs to b pipf will blodk until bnotifr
 * tirfbd rfbds tiosf bytfs, or somf prfviously-writtfn bytfs, from tif pipf is
 * systfm-dfpfndfnt bnd tifrfforf unspfdififd.  Mbny pipf implfmfntbtions will
 * bufffr up to b dfrtbin numbfr of bytfs bftwffn tif sink bnd sourdf dibnnfls,
 * but sudi bufffring siould not bf bssumfd.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss Pipf {

    /**
     * A dibnnfl rfprfsfnting tif rfbdbblf fnd of b {@link Pipf}.
     *
     * @sindf 1.4
     */
    publid stbtid bbstrbdt dlbss SourdfCibnnfl
        fxtfnds AbstrbdtSflfdtbblfCibnnfl
        implfmfnts RfbdbblfBytfCibnnfl, SdbttfringBytfCibnnfl
    {
        /**
         * Construdts b nfw instbndf of tiis dlbss.
         *
         * @pbrbm  providfr
         *         Tif sflfdtor providfr
         */
        protfdtfd SourdfCibnnfl(SflfdtorProvidfr providfr) {
            supfr(providfr);
        }

        /**
         * Rfturns bn opfrbtion sft idfntifying tiis dibnnfl's supportfd
         * opfrbtions.
         *
         * <p> Pipf-sourdf dibnnfls only support rfbding, so tiis mftiod
         * rfturns {@link SflfdtionKfy#OP_READ}.  </p>
         *
         * @rfturn  Tif vblid-opfrbtion sft
         */
        publid finbl int vblidOps() {
            rfturn SflfdtionKfy.OP_READ;
        }

    }

    /**
     * A dibnnfl rfprfsfnting tif writbblf fnd of b {@link Pipf}.
     *
     * @sindf 1.4
     */
    publid stbtid bbstrbdt dlbss SinkCibnnfl
        fxtfnds AbstrbdtSflfdtbblfCibnnfl
        implfmfnts WritbblfBytfCibnnfl, GbtifringBytfCibnnfl
    {
        /**
         * Initiblizfs b nfw instbndf of tiis dlbss.
         *
         * @pbrbm  providfr
         *         Tif sflfdtor providfr
         */
        protfdtfd SinkCibnnfl(SflfdtorProvidfr providfr) {
            supfr(providfr);
        }

        /**
         * Rfturns bn opfrbtion sft idfntifying tiis dibnnfl's supportfd
         * opfrbtions.
         *
         * <p> Pipf-sink dibnnfls only support writing, so tiis mftiod rfturns
         * {@link SflfdtionKfy#OP_WRITE}.  </p>
         *
         * @rfturn  Tif vblid-opfrbtion sft
         */
        publid finbl int vblidOps() {
            rfturn SflfdtionKfy.OP_WRITE;
        }

    }

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd Pipf() { }

    /**
     * Rfturns tiis pipf's sourdf dibnnfl.
     *
     * @rfturn  Tiis pipf's sourdf dibnnfl
     */
    publid bbstrbdt SourdfCibnnfl sourdf();

    /**
     * Rfturns tiis pipf's sink dibnnfl.
     *
     * @rfturn  Tiis pipf's sink dibnnfl
     */
    publid bbstrbdt SinkCibnnfl sink();

    /**
     * Opfns b pipf.
     *
     * <p> Tif nfw pipf is drfbtfd by invoking tif {@link
     * jbvb.nio.dibnnfls.spi.SflfdtorProvidfr#opfnPipf opfnPipf} mftiod of tif
     * systfm-widf dffbult {@link jbvb.nio.dibnnfls.spi.SflfdtorProvidfr}
     * objfdt.  </p>
     *
     * @rfturn  A nfw pipf
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid Pipf opfn() tirows IOExdfption {
        rfturn SflfdtorProvidfr.providfr().opfnPipf();
    }

}
