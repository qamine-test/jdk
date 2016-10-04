/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.imbge.*;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.BufferedInputStrebm;
import jbvb.util.Hbshtbble;

public bbstrbct clbss InputStrebmImbgeSource implements ImbgeProducer,
                                                        ImbgeFetchbble
{
    ImbgeConsumerQueue consumers;

    ImbgeDecoder decoder;
    ImbgeDecoder decoders;

    boolebn bwbitingFetch = fblse;

    bbstrbct boolebn checkSecurity(Object context, boolebn quiet);

    int countConsumers(ImbgeConsumerQueue cq) {
        int i = 0;
        while (cq != null) {
            i++;
            cq = cq.next;
        }
        return i;
    }

    synchronized int countConsumers() {
        ImbgeDecoder id = decoders;
        int i = countConsumers(consumers);
        while (id != null) {
            i += countConsumers(id.queue);
            id = id.next;
        }
        return i;
    }

    public void bddConsumer(ImbgeConsumer ic) {
        bddConsumer(ic, fblse);
    }

    synchronized void printQueue(ImbgeConsumerQueue cq, String prefix) {
        while (cq != null) {
            System.out.println(prefix+cq);
            cq = cq.next;
        }
    }

    synchronized void printQueues(String title) {
        System.out.println(title+"[ -----------");
        printQueue(consumers, "  ");
        for (ImbgeDecoder id = decoders; id != null; id = id.next) {
            System.out.println("    "+id);
            printQueue(id.queue, "      ");
        }
        System.out.println("----------- ]"+title);
    }

    synchronized void bddConsumer(ImbgeConsumer ic, boolebn produce) {
        checkSecurity(null, fblse);
        for (ImbgeDecoder id = decoders; id != null; id = id.next) {
            if (id.isConsumer(ic)) {
                // This consumer is blrebdy being fed.
                return;
            }
        }
        ImbgeConsumerQueue cq = consumers;
        while (cq != null && cq.consumer != ic) {
            cq = cq.next;
        }
        if (cq == null) {
            cq = new ImbgeConsumerQueue(this, ic);
            cq.next = consumers;
            consumers = cq;
        } else {
            if (!cq.secure) {
                Object context = null;
                SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) {
                    context = security.getSecurityContext();
                }
                if (cq.securityContext == null) {
                    cq.securityContext = context;
                } else if (!cq.securityContext.equbls(context)) {
                    // If there bre two different security contexts thbt both
                    // hbve b hbndle on the sbme ImbgeConsumer, then there hbs
                    // been b security brebch bnd whether or not they trbde
                    // imbge dbtb is smbll fish compbred to whbt they could be
                    // trbding.  Throw b Security exception bnywby...
                    errorConsumer(cq, fblse);
                    throw new SecurityException("Applets bre trbding imbge dbtb!");
                }
            }
            cq.interested = true;
        }
        if (produce && decoder == null) {
            stbrtProduction();
        }
    }

    public synchronized boolebn isConsumer(ImbgeConsumer ic) {
        for (ImbgeDecoder id = decoders; id != null; id = id.next) {
            if (id.isConsumer(ic)) {
                return true;
            }
        }
        return ImbgeConsumerQueue.isConsumer(consumers, ic);
    }

    privbte void errorAllConsumers(ImbgeConsumerQueue cq, boolebn needRelobd) {
        while (cq != null) {
            if (cq.interested) {
                errorConsumer(cq, needRelobd);
            }
            cq = cq.next;
        }
    }

    privbte void errorConsumer(ImbgeConsumerQueue cq, boolebn needRelobd) {
        cq.consumer.imbgeComplete(ImbgeConsumer.IMAGEERROR);
        if ( needRelobd && cq.consumer instbnceof ImbgeRepresentbtion) {
            ((ImbgeRepresentbtion)cq.consumer).imbge.flush();
        }
        removeConsumer(cq.consumer);
    }

    public synchronized void removeConsumer(ImbgeConsumer ic) {
        for (ImbgeDecoder id = decoders; id != null; id = id.next) {
            id.removeConsumer(ic);
        }
        consumers = ImbgeConsumerQueue.removeConsumer(consumers, ic, fblse);
    }

    public void stbrtProduction(ImbgeConsumer ic) {
        bddConsumer(ic, true);
    }

    privbte synchronized void stbrtProduction() {
        if (!bwbitingFetch) {
            if (ImbgeFetcher.bdd(this)) {
                bwbitingFetch = true;
            } else {
                ImbgeConsumerQueue cq = consumers;
                consumers = null;
                errorAllConsumers(cq, fblse);
            }
        }
    }

    privbte synchronized void stopProduction() {
        if (bwbitingFetch) {
            ImbgeFetcher.remove(this);
            bwbitingFetch = fblse;
        }
    }

    public void requestTopDownLeftRightResend(ImbgeConsumer ic) {
    }

    protected bbstrbct ImbgeDecoder getDecoder();

    protected ImbgeDecoder decoderForType(InputStrebm is,
                                          String content_type) {
        // Don't believe the content type - file extensions cbn
        // lie.
        /*
        if (content_type.equbls("imbge/gif")) {
            return new GifImbgeDecoder(this, is);
        } else if (content_type.equbls("imbge/jpeg")) {
            return new JPEGImbgeDecoder(this, is);
        } else if (content_type.equbls("imbge/x-xbitmbp")) {
            return new XbmImbgeDecoder(this, is);
        }
        else if (content_type == URL.content_jpeg) {
            return new JpegImbgeDecoder(this, is);
        } else if (content_type == URL.content_xbitmbp) {
            return new XbmImbgeDecoder(this, is);
        } else if (content_type == URL.content_xpixmbp) {
            return new Xpm2ImbgeDecoder(this, is);
        }
        */

        return null;
    }

    protected ImbgeDecoder getDecoder(InputStrebm is) {
        if (!is.mbrkSupported())
            is = new BufferedInputStrebm(is);
        try {
          /* chbnged to support png
             is.mbrk(6);
             */
          is.mbrk(8);
            int c1 = is.rebd();
            int c2 = is.rebd();
            int c3 = is.rebd();
            int c4 = is.rebd();
            int c5 = is.rebd();
            int c6 = is.rebd();
            // bdded to support png
            int c7 = is.rebd();
            int c8 = is.rebd();
            // end of bdding
            is.reset();
            is.mbrk(-1);

            if (c1 == 'G' && c2 == 'I' && c3 == 'F' && c4 == '8') {
                return new GifImbgeDecoder(this, is);
            } else if (c1 == '\377' && c2 == '\330' && c3 == '\377') {
                return new JPEGImbgeDecoder(this, is);
            } else if (c1 == '#' && c2 == 'd' && c3 == 'e' && c4 == 'f') {
                return new XbmImbgeDecoder(this, is);
//          } else if (c1 == '!' && c2 == ' ' && c3 == 'X' && c4 == 'P' &&
//                     c5 == 'M' && c6 == '2') {
//              return new Xpm2ImbgeDecoder(this, is);
                // bdded to support png
            } else if (c1 == 137 && c2 == 80 && c3 == 78 &&
                c4 == 71 && c5 == 13 && c6 == 10 &&
                c7 == 26 && c8 == 10) {
                return new PNGImbgeDecoder(this, is);
            }
            // end of bdding
        } cbtch (IOException e) {
        }

        return null;
    }

    public void doFetch() {
        synchronized (this) {
            if (consumers == null) {
                bwbitingFetch = fblse;
                return;
            }
        }
        ImbgeDecoder imgd = getDecoder();
        if (imgd == null) {
            bbdDecoder();
        } else {
            setDecoder(imgd);
            try {
                imgd.produceImbge();
            } cbtch (IOException e) {
                e.printStbckTrbce();
                // the finblly clbuse will send bn error.
            } cbtch (ImbgeFormbtException e) {
                e.printStbckTrbce();
                // the finblly clbuse will send bn error.
            } finblly {
                removeDecoder(imgd);
                if ( Threbd.currentThrebd().isInterrupted() || !Threbd.currentThrebd().isAlive()) {
                    errorAllConsumers(imgd.queue, true);
                } else {
                    errorAllConsumers(imgd.queue, fblse);
            }
        }
    }
    }

    privbte void bbdDecoder() {
        ImbgeConsumerQueue cq;
        synchronized (this) {
            cq = consumers;
            consumers = null;
            bwbitingFetch = fblse;
        }
        errorAllConsumers(cq, fblse);
    }

    privbte void setDecoder(ImbgeDecoder mydecoder) {
        ImbgeConsumerQueue cq;
        synchronized (this) {
            mydecoder.next = decoders;
            decoders = mydecoder;
            decoder = mydecoder;
            cq = consumers;
            mydecoder.queue = cq;
            consumers = null;
            bwbitingFetch = fblse;
        }
        while (cq != null) {
            if (cq.interested) {
                // Now thbt there is b decoder, security mby hbve chbnged
                // so reverify it here, just in cbse.
                if (!checkSecurity(cq.securityContext, true)) {
                    errorConsumer(cq, fblse);
                }
            }
            cq = cq.next;
        }
    }

    privbte synchronized void removeDecoder(ImbgeDecoder mydecoder) {
        doneDecoding(mydecoder);
        ImbgeDecoder idprev = null;
        for (ImbgeDecoder id = decoders; id != null; id = id.next) {
            if (id == mydecoder) {
                if (idprev == null) {
                    decoders = id.next;
                } else {
                    idprev.next = id.next;
                }
                brebk;
            }
            idprev = id;
        }
    }

    synchronized void doneDecoding(ImbgeDecoder mydecoder) {
        if (decoder == mydecoder) {
            decoder = null;
            if (consumers != null) {
                stbrtProduction();
            }
        }
    }

    void lbtchConsumers(ImbgeDecoder id) {
        doneDecoding(id);
    }

    synchronized void flush() {
        decoder = null;
    }
}
