import gov.va.vinci.leo.regex.types.RegularExpressionType

/* An arbitrary name for this annotator. Used in the pipeline for the name of this annotation. */
name = "MyRegexAnnotator"

configuration {
    /* All configuration for this annotator. */
    defaults {
        /* Global for all configrations below if a property specified here is not overridden in a section below. */
        //keep the same (for patrick)
        outputType = RegularExpressionType.class.canonicalName
        case_sensitive = false
        matchedPatternFeatureName = "pattern"
        concept_feature_name = "concept"
        groupFeatureName = "group"
    }

    /* An arbitrary name for this set of patterns/config. */

    "Score_concept" {
        expressions = [
                'NIH+S*(\\s*(stroke\\s*|score\\s*|scale\\s*|total\\s*|severity\\s*)*)?',
                'NIH( |-)?SS',
                'NIH+S*\\b',
                'STROKE SCALE \\(NIHSS\\) TOTAL SCORE',
                'NIH Stroke Scale rating for this patient:',
                'NIH Stroke Scale Total',
                'stroke severity',
                'NIH+S*\\s*stroke\\s*scale',
                'NIH+S*\\s*stroke\\s*score',
                'Enter NIHSS Score here',
                'stroke\\s*scale(\\s*score)?',
                'National Institutes? of Health Stroke Scale \\(NIHSS\\) score',
                'NIH+S*\\s*score',
                '\\** NIH Stroke Scale: \\**\\s*'

        ]
        outputType = "gov.va.vinci.stroke.types.Score"
    }

    "Concept_word" {
        expressions = [
                'scored?'
                , '\\bis\\b'
                , '\\bwas\\b(\\s*(scored|measured))?'
                , '\\bof\\b'
                , 'remain\\w*'
                , 'continu\\w*'
                , 'increas\\w*\\b(?! *by)'
                , 'decreas\\w*\\b(?! *by)'
                , '\\bimprov\\w*\\b'
                , '\\bon discharge\\b'
                , '\\bon admission'
                , '\\bhere\\b'
        ]

        concept_feature_value = "Score_word"
        outputType = "gov.va.vinci.stroke.types.Score_word"
    }

    "Score-continuous" {
        expressions = [
                '~?\\b\\d{1,2}\\b',
                '(?<=nih ?ss)\\d{1,2}\\b'
        ]
        concept_feature_value = "Score_number"
        outputType = "gov.va.vinci.stroke.types.Score_score"
    }

    "Score-categorical" {
        expressions = [
                'zero',
                '\\bn/0\\b'
                ,'\\blow\\b'
        ]
        concept_feature_value = "0"
        outputType = "gov.va.vinci.stroke.types.Score_score"
    }



    "Exclude" {
        expressions = [
                'NYHA',
                '\\d+.\\d{2,}',
                'aha',
                'hf',
                'heart failure',
                ': *\\[\\] *yes,?',
                '\\s*episodes?',
                '\\s*times',
                '\\s*nights?',
                '\\s*/?days?',
                '\\s*/?weeks?',
                '\\s*/?wks?',
                '\\s*months?',
                '\\s*minutes?',
                '\\s*hours?',
                '\\s*years?',
                '\\d+ *\\b(mm|cm|gram|hr|mg|f|c|d)s?\\b',
                'x *(/ *)?(weeks?|wks?)', // stroke 2x/week
                '.\\dx?/?\\s*(time|we*k|day|month|minute|y\\w*)', // stroke 2-3x/week
                '\\b\\d{1,2}/\\d{1,2}\\b',
                '%',
                '-[5-9]',
                '\\d\\d/\\d\\d+',
                '\\d+\\.\\d+',
                '\\d+.\\d+.\\d+',
                '\\( ?\\d+ ?\\) ?((\\d+|\\-|\\*) ?)+',
                'congestive\\s*heart\\s*failure,\\s*hypertension,\\s*age,\\s*diabetes,\\s*stroke',
                'prior\\s*stroke',
                'chads2',
                'chads\\s*risk',
                'Cincinnati\\s*prehospital',
                'Cincinnati',
                'ABCD',
                'VAS' ,
                '\\blabs',
                '\\bage\\b',
                '\\d+\\s*-\\s*\\d+'
                , '\\b(jan(uary)?|feb(ruary)?|mar(ch)?|apr(il)?|may|june?|july?|aug(ust)?|sep(tember)|oct(ober)?|nov(ember)?|dec(ember)?)\\b'
                , 'icd-\\d+'
                , '\\+\\d+'
                , 'stroke\\s*management'
                , 'stroke\\s*education'
                , 'stroke 1\\b',
                'American\\s*Stroke\\s*Association',
                '\\bhr\\b',
                'Stroke LOC:',
                'Stroke Motor Arm:',
                'Stroke Motor Leg:',
                'Stroke LOC Questions: ',
                'Stroke Limb Ataxia:',
                'Stroke LOC Commands:',
                'Stroke Sensory:',
                'Stroke Best Gaze:',
                'Stroke Best Language:',
                'Stroke Visual:',
                'Stroke Dysarthria:',
                'Stroke Facial Palsy:',
                'Stroke Extinct/Inattend:',
                'Level Of Consciousness'
,
                // Changes after Iter 3
                '(?<=\\r\\n?) *\\d{1,2}(\\)|\\.|\\-)'  //most often this is item list, but sometimes it deletes relevant entries. Keeping it because FP > FN

        ]
        concept_feature_value = "Score_exclude"
        outputType = "gov.va.vinci.stroke.types.Exclude"
    }
}

