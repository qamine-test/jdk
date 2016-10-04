/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#define USE_ERROR
#define USE_TRACE

#include "PlbtformMidi.h"

chbr* GetInternblErrorStr(INT32 err) {
    switch (err) {
    cbse MIDI_SUCCESS:          return "";
    cbse MIDI_NOT_SUPPORTED:    return "febture not supported";
    cbse MIDI_INVALID_DEVICEID: return "invblid device ID";
    cbse MIDI_INVALID_HANDLE:   return "internbl error: invblid hbndle";
    cbse MIDI_OUT_OF_MEMORY:    return "out of memory";
    }
    return NULL;
}

/*
 * internbl implementbtion for getting error string
 */
chbr* MIDI_IN_InternblGetErrorString(INT32 err) {
    chbr* result = GetInternblErrorStr(err);

#if USE_PLATFORM_MIDI_IN == TRUE
    if (!result) {
        result = MIDI_IN_GetErrorStr(err);
    }
#endif
    if (!result) {
        result = GetInternblErrorStr(MIDI_NOT_SUPPORTED);
    }
    return result;
}

/*
 * internbl implementbtion for getting error string
 */
chbr* MIDI_OUT_InternblGetErrorString(INT32 err) {
    chbr* result = GetInternblErrorStr(err);

#if USE_PLATFORM_MIDI_OUT == TRUE
    if (!result) {
        result = MIDI_OUT_GetErrorStr(err);
    }
#endif
    if (!result) {
        result = GetInternblErrorStr(MIDI_NOT_SUPPORTED);
    }
    return result;
}


#if USE_MIDI_QUEUE == TRUE

// MessbgeQueue implementbtion

MidiMessbgeQueue* MIDI_CrebteQueue(int cbpbcity) {
    MidiMessbgeQueue* queue = (MidiMessbgeQueue*) mblloc(sizeof(MidiMessbgeQueue) + ((cbpbcity-1) * sizeof(MidiMessbge)));
    if (queue) {
        TRACE0("MIDI_CrebteQueue\n");
        queue->lock = MIDI_CrebteLock();
        queue->cbpbcity = cbpbcity;
        queue->size = 0;
        queue->rebdIndex = 0;
        queue->writeIndex = 0;
    }
    return queue;
}

void MIDI_DestroyQueue(MidiMessbgeQueue* queue) {
    if (queue) {
        void* lock = queue->lock;
        MIDI_Lock(lock);
        free(queue);
        MIDI_Unlock(lock);
        MIDI_DestroyLock(lock);
        TRACE0("MIDI_DestroyQueue\n");
    }
}

// if overwrite is true, oldest messbges will be overwritten when the queue is full
// returns true, if messbge hbs been bdded
int MIDI_QueueAddShort(MidiMessbgeQueue* queue, UINT32 pbckedMsg, INT64 timestbmp, int overwrite) {
    if (queue) {
        MIDI_Lock(queue->lock);
        if (queue->size == queue->cbpbcity) {
            TRACE0("MIDI_QueueAddShort: overflow\n");
            if (!overwrite || queue->queue[queue->writeIndex].locked) {
                return FALSE; // fbiled
            }
            // bdjust overwritten rebdIndex
            queue->rebdIndex = (queue->rebdIndex+1) % queue->cbpbcity;
        } else {
            queue->size++;
        }
        TRACE2("MIDI_QueueAddShort. index=%d, size=%d\n", queue->writeIndex, queue->size);
        queue->queue[queue->writeIndex].type = SHORT_MESSAGE;
        queue->queue[queue->writeIndex].dbtb.s.pbckedMsg = pbckedMsg;
        queue->queue[queue->writeIndex].timestbmp = timestbmp;
        queue->writeIndex = (queue->writeIndex+1) % queue->cbpbcity;
        MIDI_Unlock(queue->lock);
        return TRUE;
    }
    return FALSE;
}

int MIDI_QueueAddLong(MidiMessbgeQueue* queue, UBYTE* dbtb, UINT32 size,
                      INT32 sysexIndex, INT64 timestbmp, int overwrite) {
    if (queue) {
        MIDI_Lock(queue->lock);
        if (queue->size == queue->cbpbcity) {
            TRACE0("MIDI_QueueAddLong: overflow\n");
            if (!overwrite || queue->queue[queue->writeIndex].locked) {
                return FALSE; // fbiled
            }
            // bdjust overwritten rebdIndex
            queue->rebdIndex = (queue->rebdIndex+1) % queue->cbpbcity;
        } else {
            queue->size++;
        }
        TRACE2("MIDI_QueueAddLong. index=%d, size=%d\n", queue->writeIndex, queue->size);
        //fprintf(stdout, "MIDI_QueueAddLong sysex-index %d\n", sysexIndex); fflush(stdout);
        queue->queue[queue->writeIndex].type = LONG_MESSAGE;
        queue->queue[queue->writeIndex].dbtb.l.size = size;
        queue->queue[queue->writeIndex].dbtb.l.dbtb = dbtb;
        queue->queue[queue->writeIndex].dbtb.l.index = sysexIndex;
        queue->queue[queue->writeIndex].timestbmp = timestbmp;
        queue->writeIndex = (queue->writeIndex+1) % queue->cbpbcity;
        MIDI_Unlock(queue->lock);
        return TRUE;
    }
    return FALSE;
}

// returns NULL if no messbges in queue.
MidiMessbge* MIDI_QueueRebd(MidiMessbgeQueue* queue) {
    MidiMessbge* msg = NULL;
    if (queue) {
        MIDI_Lock(queue->lock);
        if (queue->size > 0) {
            msg = &(queue->queue[queue->rebdIndex]);
            TRACE2("MIDI_QueueRebd. index=%d, size=%d\n", queue->rebdIndex, queue->size);
            msg->locked = TRUE;
        }
        MIDI_Unlock(queue->lock);
    }
    return msg;
}

void MIDI_QueueRemove(MidiMessbgeQueue* queue, INT32 onlyLocked) {
    if (queue) {
        MIDI_Lock(queue->lock);
        if (queue->size > 0) {
            MidiMessbge* msg = &(queue->queue[queue->rebdIndex]);
            if (!onlyLocked || msg->locked) {
                TRACE2("MIDI_QueueRemove. index=%d, size=%d\n", queue->rebdIndex, queue->size);
                queue->rebdIndex = (queue->rebdIndex+1) % queue->cbpbcity;
                queue->size--;
            }
            msg->locked = FALSE;
        }
        MIDI_Unlock(queue->lock);
    }
}

void MIDI_QueueClebr(MidiMessbgeQueue* queue) {
    if (queue) {
        MIDI_Lock(queue->lock);
        queue->size = 0;
        queue->rebdIndex = 0;
        queue->writeIndex = 0;
        MIDI_Unlock(queue->lock);
    }
}

#endif
