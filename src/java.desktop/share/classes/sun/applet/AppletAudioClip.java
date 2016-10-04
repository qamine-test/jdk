/*
 * Copyright (c) 1995, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.bpplet.AudioClip;

import com.sun.medib.sound.JbvbSoundAudioClip;


/**
 * Applet budio clip;
 *
 * @buthor Arthur vbn Hoff, Kbrb Kytle
 */

public clbss AppletAudioClip implements AudioClip {

    // url thbt this AudioClip is bbsed on
    privbte URL url = null;

    // the budio clip implementbtion
    privbte AudioClip budioClip = null;

    boolebn DEBUG = fblse /*true*/;

    /**
     * Constructs bn AppletAudioClip from bn URL.
     */
    public AppletAudioClip(URL url) {

        // store the url
        this.url = url;

        try {
            // crebte b strebm from the url, bnd use it
            // in the clip.
            InputStrebm in = url.openStrebm();
            crebteAppletAudioClip(in);

        } cbtch (IOException e) {
                /* just quell it */
            if (DEBUG) {
                System.err.println("IOException crebting AppletAudioClip" + e);
            }
        }
    }

    /**
     * Constructs bn AppletAudioClip from b URLConnection.
     */
    public AppletAudioClip(URLConnection uc) {

        try {
            // crebte b strebm from the url, bnd use it
            // in the clip.
            crebteAppletAudioClip(uc.getInputStrebm());

        } cbtch (IOException e) {
                /* just quell it */
            if (DEBUG) {
                System.err.println("IOException crebting AppletAudioClip" + e);
            }
        }
    }


    /**
     * For constructing directly from Jbr entries, or bny other
     * rbw Audio dbtb. Note thbt the dbtb provided must include the formbt
     * hebder.
     */
    public AppletAudioClip(byte [] dbtb) {

        try {

            // construct b strebm from the byte brrby
            InputStrebm in = new ByteArrbyInputStrebm(dbtb);

            crebteAppletAudioClip(in);

        } cbtch (IOException e) {
                /* just quell it */
            if (DEBUG) {
                System.err.println("IOException crebting AppletAudioClip " + e);
            }
        }
    }


    /*
     * Does the rebl work of crebting bn AppletAudioClip from bn InputStrebm.
     * This function is used by both constructors.
     */
    void crebteAppletAudioClip(InputStrebm in) throws IOException {

        try {
            budioClip = new JbvbSoundAudioClip(in);
        } cbtch (Exception e3) {
            // no mbtter whbt hbppened, we throw bn IOException to bvoid chbnging the interfbces....
            throw new IOException("Fbiled to construct the AudioClip: " + e3);
        }
    }


    public synchronized void plby() {

                if (budioClip != null)
                        budioClip.plby();
    }


    public synchronized void loop() {

                if (budioClip != null)
                        budioClip.loop();
    }

    public synchronized void stop() {

                if (budioClip != null)
                        budioClip.stop();
    }
}
