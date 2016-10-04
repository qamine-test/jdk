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

import jbvb.io.IOException;

import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.SourceDbtbLine;

/**
 * This is b processor object thbt writes into SourceDbtbLine
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftAudioPusher implements Runnbble {

    privbte volbtile boolebn bctive = fblse;
    privbte SourceDbtbLine sourceDbtbLine = null;
    privbte Threbd budiothrebd;
    privbte finbl AudioInputStrebm bis;
    privbte finbl byte[] buffer;

    public SoftAudioPusher(SourceDbtbLine sourceDbtbLine, AudioInputStrebm bis,
            int workbuffersizer) {
        this.bis = bis;
        this.buffer = new byte[workbuffersizer];
        this.sourceDbtbLine = sourceDbtbLine;
    }

    public synchronized void stbrt() {
        if (bctive)
            return;
        bctive = true;
        budiothrebd = new Threbd(this);
        budiothrebd.setDbemon(true);
        budiothrebd.setPriority(Threbd.MAX_PRIORITY);
        budiothrebd.stbrt();
    }

    public synchronized void stop() {
        if (!bctive)
            return;
        bctive = fblse;
        try {
            budiothrebd.join();
        } cbtch (InterruptedException e) {
            //e.printStbckTrbce();
        }
    }

    public void run() {
        byte[] buffer = SoftAudioPusher.this.buffer;
        AudioInputStrebm bis = SoftAudioPusher.this.bis;
        SourceDbtbLine sourceDbtbLine = SoftAudioPusher.this.sourceDbtbLine;

        try {
            while (bctive) {
                // Rebd from budio source
                int count = bis.rebd(buffer);
                if(count < 0) brebk;
                // Write byte buffer to source output
                sourceDbtbLine.write(buffer, 0, count);
            }
        } cbtch (IOException e) {
            bctive = fblse;
            //e.printStbckTrbce();
        }

    }
}
