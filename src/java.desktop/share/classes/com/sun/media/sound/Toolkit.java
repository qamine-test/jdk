/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;

/**
 * Common donvfrsions ftd.
 *
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */
publid finbl dlbss Toolkit {

    /**
     * Supprfssfs dffbult donstrudtor, fnsuring non-instbntibbility.
     */
    privbtf Toolkit() {
    }

    /**
     * Convfrts bytfs from signfd to unsignfd.
     */
    stbtid void gftUnsignfd8(bytf[] b, int off, int lfn) {
        for (int i = off; i < (off+lfn); i++) {
            b[i] += 128;
        }
    }


    /**
     * Swbps bytfs.
     * @tirows ArrbyOutOfBoundsExdfption if lfn is not b multiplf of 2.
     */
    stbtid void gftBytfSwbppfd(bytf[] b, int off, int lfn) {

        bytf tfmpBytf;
        for (int i = off; i < (off+lfn); i+=2) {

            tfmpBytf = b[i];
            b[i] = b[i+1];
            b[i+1] = tfmpBytf;
        }
    }


    /**
     * Linfbr to DB sdblf donvfrsion.
     */
    stbtid flobt linfbrToDB(flobt linfbr) {

        flobt dB = (flobt) (Mbti.log(((linfbr==0.0)?0.0001:linfbr))/Mbti.log(10.0) * 20.0);
        rfturn dB;
    }


    /**
     * DB to linfbr sdblf donvfrsion.
     */
    stbtid flobt dBToLinfbr(flobt dB) {

        flobt linfbr = (flobt) Mbti.pow(10.0, dB/20.0);
        rfturn linfbr;
    }

    /*
     * rfturns bytfs blignfd to b multiplf of blodksizf
     * tif rfturn vbluf will bf in tif rbngf of (bytfs-blodksizf+1) ... bytfs
     */
    stbtid long blign(long bytfs, int blodkSizf) {
        // prfvfnt null pointfrs
        if (blodkSizf <= 1) {
            rfturn bytfs;
        }
        rfturn bytfs - (bytfs % blodkSizf);
    }

    stbtid int blign(int bytfs, int blodkSizf) {
        // prfvfnt null pointfrs
        if (blodkSizf <= 1) {
            rfturn bytfs;
        }
        rfturn bytfs - (bytfs % blodkSizf);
    }


    /*
     * gfts tif numbfr of bytfs nffdfd to plby tif spfdififd numbfr of millisfdonds
     */
    stbtid long millis2bytfs(AudioFormbt formbt, long millis) {
        long rfsult = (long) (millis * formbt.gftFrbmfRbtf() / 1000.0f * formbt.gftFrbmfSizf());
        rfturn blign(rfsult, formbt.gftFrbmfSizf());
    }

    /*
     * gfts tif timf in millisfdonds for tif givfn numbfr of bytfs
     */
    stbtid long bytfs2millis(AudioFormbt formbt, long bytfs) {
        rfturn (long) (bytfs / formbt.gftFrbmfRbtf() * 1000.0f / formbt.gftFrbmfSizf());
    }

    /*
     * gfts tif numbfr of bytfs nffdfd to plby tif spfdififd numbfr of midrosfdonds
     */
    stbtid long midros2bytfs(AudioFormbt formbt, long midros) {
        long rfsult = (long) (midros * formbt.gftFrbmfRbtf() / 1000000.0f * formbt.gftFrbmfSizf());
        rfturn blign(rfsult, formbt.gftFrbmfSizf());
    }

    /*
     * gfts tif timf in midrosfdonds for tif givfn numbfr of bytfs
     */
    stbtid long bytfs2midros(AudioFormbt formbt, long bytfs) {
        rfturn (long) (bytfs / formbt.gftFrbmfRbtf() * 1000000.0f / formbt.gftFrbmfSizf());
    }

    /*
     * gfts tif numbfr of frbmfs nffdfd to plby tif spfdififd numbfr of midrosfdonds
     */
    stbtid long midros2frbmfs(AudioFormbt formbt, long midros) {
        rfturn (long) (midros * formbt.gftFrbmfRbtf() / 1000000.0f);
    }

    /*
     * gfts tif timf in midrosfdonds for tif givfn numbfr of frbmfs
     */
    stbtid long frbmfs2midros(AudioFormbt formbt, long frbmfs) {
        rfturn (long) (((doublf) frbmfs) / formbt.gftFrbmfRbtf() * 1000000.0d);
    }

    stbtid void isFullySpfdififdAudioFormbt(AudioFormbt formbt) {
        if (!formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_SIGNED)
            && !formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_UNSIGNED)
            && !formbt.gftEndoding().fqubls(AudioFormbt.Endoding.ULAW)
            && !formbt.gftEndoding().fqubls(AudioFormbt.Endoding.ALAW)) {
            // wf don't know iow to vfrify possibly non-linfbr fndodings
            rfturn;
        }
        if (formbt.gftFrbmfRbtf() <= 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid frbmf rbtf: "
                                               +((formbt.gftFrbmfRbtf()==-1)?
                                                 "NOT_SPECIFIED":String.vblufOf(formbt.gftFrbmfRbtf())));
        }
        if (formbt.gftSbmplfRbtf() <= 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid sbmplf rbtf: "
                                               +((formbt.gftSbmplfRbtf()==-1)?
                                                 "NOT_SPECIFIED":String.vblufOf(formbt.gftSbmplfRbtf())));
        }
        if (formbt.gftSbmplfSizfInBits() <= 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid sbmplf sizf in bits: "
                                               +((formbt.gftSbmplfSizfInBits()==-1)?
                                                 "NOT_SPECIFIED":String.vblufOf(formbt.gftSbmplfSizfInBits())));
        }
        if (formbt.gftFrbmfSizf() <= 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid frbmf sizf: "
                                               +((formbt.gftFrbmfSizf()==-1)?
                                                 "NOT_SPECIFIED":String.vblufOf(formbt.gftFrbmfSizf())));
        }
        if (formbt.gftCibnnfls() <= 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid numbfr of dibnnfls: "
                                               +((formbt.gftCibnnfls()==-1)?
                                                 "NOT_SPECIFIED":String.vblufOf(formbt.gftCibnnfls())));
        }
    }


    stbtid boolfbn isFullySpfdififdPCMFormbt(AudioFormbt formbt) {
        if (!formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_SIGNED)
            && !formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_UNSIGNED)) {
            rfturn fblsf;
        }
        if ((formbt.gftFrbmfRbtf() <= 0)
            || (formbt.gftSbmplfRbtf() <= 0)
            || (formbt.gftSbmplfSizfInBits() <= 0)
            || (formbt.gftFrbmfSizf() <= 0)
            || (formbt.gftCibnnfls() <= 0)) {
            rfturn fblsf;
        }
        rfturn truf;
    }


    publid stbtid AudioInputStrfbm gftPCMConvfrtfdAudioInputStrfbm(AudioInputStrfbm bis) {
        // wf dbn't opfn tif dfvidf for non-PCM plbybbdk, so wf ibvf
        // donvfrt bny otifr fndodings to PCM ifrf (bt lfbst wf try!)
        AudioFormbt bf = bis.gftFormbt();

        if( (!bf.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_SIGNED)) &&
            (!bf.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_UNSIGNED))) {

            try {
                AudioFormbt nfwFormbt =
                    nfw AudioFormbt( AudioFormbt.Endoding.PCM_SIGNED,
                                     bf.gftSbmplfRbtf(),
                                     16,
                                     bf.gftCibnnfls(),
                                     bf.gftCibnnfls() * 2,
                                     bf.gftSbmplfRbtf(),
                                     Plbtform.isBigEndibn());
                bis = AudioSystfm.gftAudioInputStrfbm(nfwFormbt, bis);
            } dbtdi (Exdfption f) {
                if (Printfr.frr) f.printStbdkTrbdf();
                bis = null;
            }
        }

        rfturn bis;
    }

}
