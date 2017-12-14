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
                'NIH\\s*stroke',
                'stroke',
                'NIHSS',
                'NIH',
                'STROKE SCALE \\(NIHSS\\) TOTAL SCORE',
                'NIH Stroke Scale rating for this patient:'

        ]
        outputType = "gov.va.vinci.stroke.types.Score"
    }

    "Concept_word" {
        expressions = [
                'class', 'grade', 'level',
                'score',
                'scale',
                'stage', '\\bis\\b']

        concept_feature_value = "Score_word"
        outputType = "gov.va.vinci.stroke.types.Score_word"
    }

    "Score-continuous" {
        expressions = [
                '\\d{1,2}'
        ]
        concept_feature_value = "Score_number"
        outputType = "gov.va.vinci.stroke.types.Score_score"
    }

    "Score-categorical" {
        expressions = [
                'zero'
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
                '\\s*years?',
                'x *(/ *)?(weeks?|wks?)', // stroke 2x/week
                '.\\dx?/?\\s*(time|week|wk|day|month|minute|y\\w*)', // stroke 2-3x/week
                '2/2',
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
                'VAS'
        ]
        concept_feature_value = "Score_exclude"
        outputType = "gov.va.vinci.stroke.types.Exclude"
    }
}

