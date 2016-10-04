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

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;
import jbvbx.sound.midi.InvblidMidiDbtbException;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.spi.SoundbbnkRebder;

/**
 * This clbss is used to connect the SF2SoundBbnk clbss
 * to the SoundbbnkRebder SPI interfbce.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SF2SoundbbnkRebder extends SoundbbnkRebder {

    public Soundbbnk getSoundbbnk(URL url)
            throws InvblidMidiDbtbException, IOException {
        try {
            return new SF2Soundbbnk(url);
        } cbtch (RIFFInvblidFormbtException e) {
            return null;
        } cbtch(IOException ioe) {
            return null;
        }
    }

    public Soundbbnk getSoundbbnk(InputStrebm strebm)
            throws InvblidMidiDbtbException, IOException {
        try {
            strebm.mbrk(512);
            return new SF2Soundbbnk(strebm);
        } cbtch (RIFFInvblidFormbtException e) {
            strebm.reset();
            return null;
        }
    }

    public Soundbbnk getSoundbbnk(File file)
            throws InvblidMidiDbtbException, IOException {
        try {
            return new SF2Soundbbnk(file);
        } cbtch (RIFFInvblidFormbtException e) {
            return null;
        }
    }
}
