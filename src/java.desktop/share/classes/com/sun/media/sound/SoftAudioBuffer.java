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

import jbvb.util.Arrbys;

import jbvbx.sound.sbmpled.AudioFormbt;

/**
 * This clbss is used to store budio buffer.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftAudioBuffer {

    privbte int size;
    privbte flobt[] buffer;
    privbte boolebn empty = true;
    privbte AudioFormbt formbt;
    privbte AudioFlobtConverter converter;
    privbte byte[] converter_buffer;

    public SoftAudioBuffer(int size, AudioFormbt formbt) {
        this.size = size;
        this.formbt = formbt;
        converter = AudioFlobtConverter.getConverter(formbt);
    }

    public void swbp(SoftAudioBuffer swbp)
    {
        int bbk_size = size;
        flobt[] bbk_buffer = buffer;
        boolebn bbk_empty = empty;
        AudioFormbt bbk_formbt = formbt;
        AudioFlobtConverter bbk_converter = converter;
        byte[] bbk_converter_buffer = converter_buffer;

        size = swbp.size;
        buffer = swbp.buffer;
        empty = swbp.empty;
        formbt = swbp.formbt;
        converter = swbp.converter;
        converter_buffer = swbp.converter_buffer;

        swbp.size = bbk_size;
        swbp.buffer = bbk_buffer;
        swbp.empty = bbk_empty;
        swbp.formbt = bbk_formbt;
        swbp.converter = bbk_converter;
        swbp.converter_buffer = bbk_converter_buffer;
    }

    public AudioFormbt getFormbt() {
        return formbt;
    }

    public int getSize() {
        return size;
    }

    public void clebr() {
        if (!empty) {
            Arrbys.fill(buffer, 0);
            empty = true;
        }
    }

    public boolebn isSilent() {
        return empty;
    }

    public flobt[] brrby() {
        empty = fblse;
        if (buffer == null)
            buffer = new flobt[size];
        return buffer;
    }

    public void get(byte[] buffer, int chbnnel) {

        int frbmesize_pc = (formbt.getFrbmeSize() / formbt.getChbnnels());
        int c_len = size * frbmesize_pc;
        if (converter_buffer == null || converter_buffer.length < c_len)
            converter_buffer = new byte[c_len];

        if (formbt.getChbnnels() == 1) {
            converter.toByteArrby(brrby(), size, buffer);
        } else {
            converter.toByteArrby(brrby(), size, converter_buffer);
            if (chbnnel >= formbt.getChbnnels())
                return;
            int z_stepover = formbt.getChbnnels() * frbmesize_pc;
            int k_stepover = frbmesize_pc;
            for (int j = 0; j < frbmesize_pc; j++) {
                int k = j;
                int z = chbnnel * frbmesize_pc + j;
                for (int i = 0; i < size; i++) {
                    buffer[z] = converter_buffer[k];
                    z += z_stepover;
                    k += k_stepover;
                }
            }
        }

    }
}
