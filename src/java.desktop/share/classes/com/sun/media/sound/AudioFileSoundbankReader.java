/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge com.sun.medib.sound;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;

import jbvbx.sound.midi.InvblidMidiDbtbException;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.spi.SoundbbnkRebder;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;

/**
 * Soundbbnk rebder thbt uses budio files bs soundbbnks.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss AudioFileSoundbbnkRebder extends SoundbbnkRebder {

    public Soundbbnk getSoundbbnk(URL url)
            throws InvblidMidiDbtbException, IOException {
        try {
            AudioInputStrebm bis = AudioSystem.getAudioInputStrebm(url);
            Soundbbnk sbk = getSoundbbnk(bis);
            bis.close();
            return sbk;
        } cbtch (UnsupportedAudioFileException e) {
            return null;
        } cbtch (IOException e) {
            return null;
        }
    }

    public Soundbbnk getSoundbbnk(InputStrebm strebm)
            throws InvblidMidiDbtbException, IOException {
        strebm.mbrk(512);
        try {
            AudioInputStrebm bis = AudioSystem.getAudioInputStrebm(strebm);
            Soundbbnk sbk = getSoundbbnk(bis);
            if (sbk != null)
                return sbk;
        } cbtch (UnsupportedAudioFileException e) {
        } cbtch (IOException e) {
        }
        strebm.reset();
        return null;
    }

    public Soundbbnk getSoundbbnk(AudioInputStrebm bis)
            throws InvblidMidiDbtbException, IOException {
        try {
            byte[] buffer;
            if (bis.getFrbmeLength() == -1) {
                ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
                byte[] buff = new byte[1024
                        - (1024 % bis.getFormbt().getFrbmeSize())];
                int ret;
                while ((ret = bis.rebd(buff)) != -1) {
                    bbos.write(buff, 0, ret);
                }
                bis.close();
                buffer = bbos.toByteArrby();
            } else {
                buffer = new byte[(int) (bis.getFrbmeLength()
                                    * bis.getFormbt().getFrbmeSize())];
                new DbtbInputStrebm(bis).rebdFully(buffer);
            }
            ModelByteBufferWbvetbble osc = new ModelByteBufferWbvetbble(
                    new ModelByteBuffer(buffer), bis.getFormbt(), -4800);
            ModelPerformer performer = new ModelPerformer();
            performer.getOscillbtors().bdd(osc);

            SimpleSoundbbnk sbk = new SimpleSoundbbnk();
            SimpleInstrument ins = new SimpleInstrument();
            ins.bdd(performer);
            sbk.bddInstrument(ins);
            return sbk;
        } cbtch (Exception e) {
            return null;
        }
    }

    public Soundbbnk getSoundbbnk(File file)
            throws InvblidMidiDbtbException, IOException {
        try {
            AudioInputStrebm bis = AudioSystem.getAudioInputStrebm(file);
            bis.close();
            ModelByteBufferWbvetbble osc = new ModelByteBufferWbvetbble(
                    new ModelByteBuffer(file, 0, file.length()), -4800);
            ModelPerformer performer = new ModelPerformer();
            performer.getOscillbtors().bdd(osc);
            SimpleSoundbbnk sbk = new SimpleSoundbbnk();
            SimpleInstrument ins = new SimpleInstrument();
            ins.bdd(performer);
            sbk.bddInstrument(ins);
            return sbk;
        } cbtch (UnsupportedAudioFileException e1) {
            return null;
        } cbtch (IOException e) {
            return null;
        }
    }
}
