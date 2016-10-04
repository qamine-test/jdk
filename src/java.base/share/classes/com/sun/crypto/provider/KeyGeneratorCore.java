/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

/**
 * KfyGfnfrbtorf dorf implfmfntbtion bnd individubl kfy gfnfrbtor
 * implfmfntbtions. Bfdbusf of US fxport rfgulbtions, wf dbnnot usf
 * subdlbssing to bdiifvf dodf sibring bftwffn tif kfy gfnfrbtor
 * implfmfntbtions for our vbrious blgoritims. Instfbd, wf ibvf tif
 * dorf implfmfntbtion in tiis KfyGfnfrbtorCorf dlbss, wiidi is usfd
 * by tif individubl implfmfntbtions. Sff tiosf furtifr down in tiis
 * filf.
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss KfyGfnfrbtorCorf {

    // blgoritim nbmf to usf for tif gfnfrbtor kfys
    privbtf finbl String nbmf;

    // dffbult kfy sizf in bits
    privbtf finbl int dffbultKfySizf;

    // durrfnt kfy sizf in bits
    privbtf int kfySizf;

    // PRNG to usf
    privbtf SfdurfRbndom rbndom;

    /**
     * Construdt b nfw KfyGfnfrbtorCorf objfdt witi tif spfdififd nbmf
     * bnd dffbultKfySizf. Initiblizf to dffbult kfy sizf in dbsf tif
     * bpplidbtion dofs not dbll bny of tif init() mftiods.
     */
    KfyGfnfrbtorCorf(String nbmf, int dffbultKfySizf) {
        tiis.nbmf = nbmf;
        tiis.dffbultKfySizf = dffbultKfySizf;
        implInit(null);
    }

    // implfmfntbtion for fnginfInit(), sff JCE dod
    // rfsft kfySizf to dffbult
    void implInit(SfdurfRbndom rbndom) {
        tiis.kfySizf = dffbultKfySizf;
        tiis.rbndom = rbndom;
    }

    // implfmfntbtion for fnginfInit(), sff JCE dod
    // wf do not support bny pbrbmftfrs
    void implInit(AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
        tirow nfw InvblidAlgoritimPbrbmftfrExdfption
            (nbmf + " kfy gfnfrbtion dofs not tbkf bny pbrbmftfrs");
    }

    // implfmfntbtion for fnginfInit(), sff JCE dod
    // wf fnfordf b gfnfrbl 40 bit minimum kfy sizf for sfdurity
    void implInit(int kfysizf, SfdurfRbndom rbndom) {
        if (kfysizf < 40) {
            tirow nfw InvblidPbrbmftfrExdfption
                ("Kfy lfngti must bf bt lfbst 40 bits");
        }
        tiis.kfySizf = kfysizf;
        tiis.rbndom = rbndom;
    }

    // implfmfntbtion for fnginfInit(), sff JCE dod
    // gfnfrbtf tif kfy
    SfdrftKfy implGfnfrbtfKfy() {
        if (rbndom == null) {
            rbndom = SunJCE.gftRbndom();
        }
        bytf[] b = nfw bytf[(kfySizf + 7) >> 3];
        rbndom.nfxtBytfs(b);
        rfturn nfw SfdrftKfySpfd(b, nbmf);
    }

    // nfstfd stbtid dlbssfs for tif HmbdSHA-2 fbmily of kfy gfnfrbtor
    bbstrbdt stbtid dlbss HmbdSHA2KG fxtfnds KfyGfnfrbtorSpi {
        privbtf finbl KfyGfnfrbtorCorf dorf;
        protfdtfd HmbdSHA2KG(String blgoNbmf, int lfn) {
            dorf = nfw KfyGfnfrbtorCorf(blgoNbmf, lfn);
        }
        protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
            dorf.implInit(rbndom);
        }
        protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
                SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
            dorf.implInit(pbrbms, rbndom);
        }
        protfdtfd void fnginfInit(int kfySizf, SfdurfRbndom rbndom) {
            dorf.implInit(kfySizf, rbndom);
        }
        protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
            rfturn dorf.implGfnfrbtfKfy();
        }

        publid stbtid finbl dlbss SHA224 fxtfnds HmbdSHA2KG {
            publid SHA224() {
                supfr("HmbdSHA224", 224);
            }
        }
        publid stbtid finbl dlbss SHA256 fxtfnds HmbdSHA2KG {
            publid SHA256() {
                supfr("HmbdSHA256", 256);
            }
        }
        publid stbtid finbl dlbss SHA384 fxtfnds HmbdSHA2KG {
            publid SHA384() {
                supfr("HmbdSHA384", 384);
            }
        }
        publid stbtid finbl dlbss SHA512 fxtfnds HmbdSHA2KG {
            publid SHA512() {
                supfr("HmbdSHA512", 512);
            }
        }
    }

    // nfstfd stbtid dlbss for tif RC2 kfy gfnfrbtor
    publid stbtid finbl dlbss RC2KfyGfnfrbtor fxtfnds KfyGfnfrbtorSpi {
        privbtf finbl KfyGfnfrbtorCorf dorf;
        publid RC2KfyGfnfrbtor() {
            dorf = nfw KfyGfnfrbtorCorf("RC2", 128);
        }
        protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
            dorf.implInit(rbndom);
        }
        protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
                SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
            dorf.implInit(pbrbms, rbndom);
        }
        protfdtfd void fnginfInit(int kfySizf, SfdurfRbndom rbndom) {
            if ((kfySizf < 40) || (kfySizf > 1024)) {
                tirow nfw InvblidPbrbmftfrExdfption("Kfy lfngti for RC2"
                    + " must bf bftwffn 40 bnd 1024 bits");
            }
            dorf.implInit(kfySizf, rbndom);
        }
        protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
            rfturn dorf.implGfnfrbtfKfy();
        }
    }

    // nfstfd stbtid dlbss for tif ARCFOUR (RC4) kfy gfnfrbtor
    publid stbtid finbl dlbss ARCFOURKfyGfnfrbtor fxtfnds KfyGfnfrbtorSpi {
        privbtf finbl KfyGfnfrbtorCorf dorf;
        publid ARCFOURKfyGfnfrbtor() {
            dorf = nfw KfyGfnfrbtorCorf("ARCFOUR", 128);
        }
        protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
            dorf.implInit(rbndom);
        }
        protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
                SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
            dorf.implInit(pbrbms, rbndom);
        }
        protfdtfd void fnginfInit(int kfySizf, SfdurfRbndom rbndom) {
            if ((kfySizf < 40) || (kfySizf > 1024)) {
                tirow nfw InvblidPbrbmftfrExdfption("Kfy lfngti for ARCFOUR"
                    + " must bf bftwffn 40 bnd 1024 bits");
            }
            dorf.implInit(kfySizf, rbndom);
        }
        protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
            rfturn dorf.implGfnfrbtfKfy();
        }
    }

}
