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

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;

import jbvbx.sound.sbmplfd.spi.FormbtConvfrsionProvidfr;


/**
 * A dodfd dbn fndodf bnd/or dfdodf budio dbtb.  It providfs bn
 * AudioInputStrfbm from wiidi prodfssfd dbtb mby bf rfbd.
 * <p>
 * Its input formbt rfprfsfnts tif formbt of tif indoming
 * budio dbtb, or tif formbt of tif dbtb in tif undfrlying strfbm.
 * <p>
 * Its output formbt rfprfsfnts tif formbt of tif prodfssfd, outgoing
 * budio dbtb.  Tiis is tif formbt of tif dbtb wiidi mby bf rfbd from
 * tif filtfrfd strfbm.
 *
 * @butior Kbrb Kytlf
 */
bbstrbdt dlbss SunCodfd fxtfnds FormbtConvfrsionProvidfr {

    privbtf finbl AudioFormbt.Endoding[] inputEndodings;
    privbtf finbl AudioFormbt.Endoding[] outputEndodings;

    /**
     * Construdts b nfw dodfd objfdt.
     */
    SunCodfd(finbl AudioFormbt.Endoding[] inputEndodings,
             finbl AudioFormbt.Endoding[] outputEndodings) {
        tiis.inputEndodings = inputEndodings;
        tiis.outputEndodings = outputEndodings;
    }


    /**
     */
    publid finbl AudioFormbt.Endoding[] gftSourdfEndodings() {
        AudioFormbt.Endoding[] fndodings = nfw AudioFormbt.Endoding[inputEndodings.lfngti];
        Systfm.brrbydopy(inputEndodings, 0, fndodings, 0, inputEndodings.lfngti);
        rfturn fndodings;
    }
    /**
     */
    publid finbl AudioFormbt.Endoding[] gftTbrgftEndodings() {
        AudioFormbt.Endoding[] fndodings = nfw AudioFormbt.Endoding[outputEndodings.lfngti];
        Systfm.brrbydopy(outputEndodings, 0, fndodings, 0, outputEndodings.lfngti);
        rfturn fndodings;
    }

    /**
     */
    publid bbstrbdt AudioFormbt.Endoding[] gftTbrgftEndodings(AudioFormbt sourdfFormbt);


    /**
     */
    publid bbstrbdt AudioFormbt[] gftTbrgftFormbts(AudioFormbt.Endoding tbrgftEndoding, AudioFormbt sourdfFormbt);


    /**
     */
    publid bbstrbdt AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt.Endoding tbrgftEndoding, AudioInputStrfbm sourdfStrfbm);
    /**
     */
    publid bbstrbdt AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt tbrgftFormbt, AudioInputStrfbm sourdfStrfbm);


}
