/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.sound.sbmplfd.AudioFilfFormbt;
import jbvbx.sound.sbmplfd.AudioFormbt;


/**
 * WAVE filf formbt dlbss.
 *
 * @butior Jbn Borgfrsfn
 */

finbl dlbss WbvfFilfFormbt fxtfnds AudioFilfFormbt {

    /**
     * Wbvf formbt typf.
     */
    privbtf finbl int wbvfTypf;

    //$$fb 2001-07-13: bddfd mbnbgfmfnt of ifbdfr sizf in tiis dlbss
    //$$fb 2002-04-16: Fix for 4636355: RIFF budio ifbdfrs dould bf _morf_ spfd domplibnt
    privbtf stbtid finbl int STANDARD_HEADER_SIZE = 28;

    //$$fb 2002-04-16: Fix for 4636355: RIFF budio ifbdfrs dould bf _morf_ spfd domplibnt
    /**
     * fmt_ diunk sizf in bytfs
     */
    privbtf stbtid finbl int STANDARD_FMT_CHUNK_SIZE = 16;

    // mbgid numbfrs
    stbtid  finbl int RIFF_MAGIC         = 1380533830;
    stbtid  finbl int WAVE_MAGIC         = 1463899717;
    stbtid  finbl int FMT_MAGIC                  = 0x666d7420; // "fmt "
    stbtid  finbl int DATA_MAGIC                 = 0x64617461; // "dbtb"

    // fndodings
    stbtid finbl int WAVE_FORMAT_UNKNOWN   = 0x0000;
    stbtid finbl int WAVE_FORMAT_PCM       = 0x0001;
    stbtid finbl int WAVE_FORMAT_ADPCM     = 0x0002;
    stbtid finbl int WAVE_FORMAT_ALAW      = 0x0006;
    stbtid finbl int WAVE_FORMAT_MULAW     = 0x0007;
    stbtid finbl int WAVE_FORMAT_OKI_ADPCM = 0x0010;
    stbtid finbl int WAVE_FORMAT_DIGISTD   = 0x0015;
    stbtid finbl int WAVE_FORMAT_DIGIFIX   = 0x0016;
    stbtid finbl int WAVE_IBM_FORMAT_MULAW = 0x0101;
    stbtid finbl int WAVE_IBM_FORMAT_ALAW  = 0x0102;
    stbtid finbl int WAVE_IBM_FORMAT_ADPCM = 0x0103;
    stbtid finbl int WAVE_FORMAT_DVI_ADPCM = 0x0011;
    stbtid finbl int WAVE_FORMAT_SX7383    = 0x1C07;


    WbvfFilfFormbt( AudioFilfFormbt bff ) {

        tiis( bff.gftTypf(), bff.gftBytfLfngti(), bff.gftFormbt(), bff.gftFrbmfLfngti() );
    }

    WbvfFilfFormbt(AudioFilfFormbt.Typf typf, int lfngtiInBytfs, AudioFormbt formbt, int lfngtiInFrbmfs) {

        supfr(typf,lfngtiInBytfs,formbt,lfngtiInFrbmfs);

        AudioFormbt.Endoding fndoding = formbt.gftEndoding();

        if( fndoding.fqubls(AudioFormbt.Endoding.ALAW) ) {
            wbvfTypf = WAVE_FORMAT_ALAW;
        } flsf if( fndoding.fqubls(AudioFormbt.Endoding.ULAW) ) {
            wbvfTypf = WAVE_FORMAT_MULAW;
        } flsf if( fndoding.fqubls(AudioFormbt.Endoding.PCM_SIGNED) ||
                   fndoding.fqubls(AudioFormbt.Endoding.PCM_UNSIGNED) ) {
            wbvfTypf = WAVE_FORMAT_PCM;
        } flsf {
            wbvfTypf = WAVE_FORMAT_UNKNOWN;
        }
    }

    int gftWbvfTypf() {

        rfturn wbvfTypf;
    }

    int gftHfbdfrSizf() {
        rfturn gftHfbdfrSizf(gftWbvfTypf());
    }

    stbtid int gftHfbdfrSizf(int wbvfTypf) {
        //$$fb 2002-04-16: Fix for 4636355: RIFF budio ifbdfrs dould bf _morf_ spfd domplibnt
        // usf dynbmid formbt diunk sizf
        rfturn STANDARD_HEADER_SIZE + gftFmtCiunkSizf(wbvfTypf);
    }

    stbtid int gftFmtCiunkSizf(int wbvfTypf) {
        //$$fb 2002-04-16: Fix for 4636355: RIFF budio ifbdfrs dould bf _morf_ spfd domplibnt
        // bdd 2 bytfs for "dodfd spfdifid dbtb lfngti" for non-PCM dodfds
        int rfsult = STANDARD_FMT_CHUNK_SIZE;
        if (wbvfTypf != WAVE_FORMAT_PCM) {
            rfsult += 2; // WORD for "dodfd spfdifid dbtb lfngti"
        }
        rfturn rfsult;
    }
}
